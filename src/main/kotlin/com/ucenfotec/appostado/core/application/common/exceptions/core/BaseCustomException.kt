package com.ucenfotec.appostado.core.application.common.exceptions.core

import com.google.cloud.Timestamp
import org.springframework.http.HttpStatus

open class BaseCustomException(
    val code: String,
    override val message: String,
    val status : HttpStatus,
    val additionalDetails: Map<String, Any?>? = null,
    val timestamp: Timestamp = Timestamp.now()
) : RuntimeException(message)
