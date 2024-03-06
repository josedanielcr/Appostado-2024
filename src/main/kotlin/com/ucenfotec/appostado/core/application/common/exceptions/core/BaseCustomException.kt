package com.ucenfotec.appostado.core.application.common.exceptions.core

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

open class BaseCustomException(
    val code: String,
    override val message: String,
    val status : HttpStatus,
    val additionalDetails: Map<String, Any?>? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
) : RuntimeException(message)
