package com.ucenfotec.appostado.core.application.extensions.jwt

import com.azure.security.keyvault.secrets.SecretClient
import com.ucenfotec.appostado.core.application.common.exceptions.authentication.InvalidTokenProvidedException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    private val secretClient: SecretClient
) {

    fun generateToken(userId: String): String {
        val keyBytes = secretClient.getSecret("jwtKey").value.toByteArray()
        val key = Keys.hmacShaKeyFor(keyBytes)
        val now = System.currentTimeMillis()

        return  Jwts.builder()
            .id(UUID.randomUUID().toString())
            .subject(userId)
            .signWith(key)
            .expiration(Date(now + 86400000 * 5)) //5 days
            .compact()
    }

    fun validateToken(token: String?): Boolean {
        val keyBytes = secretClient.getSecret("jwtKey").value.toByteArray();
        val key = Keys.hmacShaKeyFor(keyBytes);
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload;
            true
        } catch (e: JwtException) {
            throw InvalidTokenProvidedException(
                additionalDetails = mapOf(
                    "message" to e.message
                )
            )
        }
    }
}
