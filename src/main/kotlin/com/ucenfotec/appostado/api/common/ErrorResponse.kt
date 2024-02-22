package com.ucenfotec.appostado.api.common

import java.time.LocalDateTime

data class ErrorResponse(
    val errorCode: String,
    val errorMessage: String,
    val status: Int,
    val additionalDetails: Map<String, Any?>? = null,
    val timestamp: LocalDateTime
)