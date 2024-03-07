package com.ucenfotec.appostado.api.interceptors

import com.ucenfotec.appostado.core.application.common.exceptions.authentication.InvalidTokenProvidedException
import com.ucenfotec.appostado.core.application.common.exceptions.authentication.NoTokenProvidedException
import com.ucenfotec.appostado.core.application.extensions.jwt.JwtService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor

class JwtAuthInterceptor(private val jwtService: JwtService) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        // Allow access to the auth endpoint
        val requestURI = request.requestURI;
        if (requestURI.contains("/api/v1/auth")) return true;

        val token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) throw NoTokenProvidedException();

        val jwt = token.substring(7); // Remove the "Bearer " prefix

        if (!jwtService.validateToken(jwt)) throw InvalidTokenProvidedException();
        return true;
    }
}