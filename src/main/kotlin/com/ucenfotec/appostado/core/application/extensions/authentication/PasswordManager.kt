package com.ucenfotec.appostado.core.application.extensions.authentication

import com.azure.security.keyvault.secrets.SecretClient
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

@Service
class PasswordManager(private val secretClient: SecretClient) {

    companion object {
        const val PASSWORD_ALGORITHM_KEY = "passwordAlgorithm"
        const val PASSWORD_ITERATION_COUNT_KEY = "passwordIterationCount"
        const val PASSWORD_KEY_LENGTH_KEY = "passwordKeyLength"
    }

    fun generateSaltAndHash(password: String): Pair<String, String> {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)

        val spec: KeySpec = getKeySpec(password, salt)
        val factory = SecretKeyFactory.getInstance(secretClient.getSecret(PASSWORD_ALGORITHM_KEY).value)

        val hash = factory.generateSecret(spec).encoded
        return Pair(toHex(salt), toHex(hash))
    }

    fun validatePassword(inputPassword: String, storedSalt: String, storedHash: String): Boolean {
        val salt = fromHex(storedSalt)
        val spec: KeySpec = getKeySpec(inputPassword, salt)
        val factory = SecretKeyFactory.getInstance(secretClient.getSecret(PASSWORD_ALGORITHM_KEY).value)

        val hash = factory.generateSecret(spec).encoded
        return toHex(hash) == storedHash
    }

    private fun getKeySpec(password: String, salt: ByteArray): KeySpec {
        val spec: KeySpec = PBEKeySpec(
            password.toCharArray(),
            salt,
            secretClient.getSecret(PASSWORD_ITERATION_COUNT_KEY).value.toInt(),
            secretClient.getSecret(PASSWORD_KEY_LENGTH_KEY).value.toInt()
        )
        return spec
    }

    private fun toHex(array: ByteArray): String {
        val bi = BigInteger(1, array)
        val hex = bi.toString(16)
        val paddingLength = (array.size * 2) - hex.length
        return if (paddingLength > 0) String.format("%0" + paddingLength + "d", 0) + hex else hex
    }

    private fun fromHex(hex: String): ByteArray {
        val bytes = ByteArray(hex.length / 2)
        for (i in bytes.indices) {
            bytes[i] = hex.substring(2 * i, 2 * i + 2).toInt(16).toByte()
        }
        return bytes
    }
}