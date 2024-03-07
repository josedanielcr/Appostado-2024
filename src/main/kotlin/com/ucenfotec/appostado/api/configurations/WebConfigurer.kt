package com.ucenfotec.appostado.api.configurations

import com.ucenfotec.appostado.api.interceptors.JwtAuthInterceptor
import com.ucenfotec.appostado.core.application.extensions.jwt.JwtService
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfigurer(
    private val jwtService: JwtService
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(JwtAuthInterceptor(jwtService))
    }
}