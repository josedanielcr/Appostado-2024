package com.ucenfotec.appostado.core.application.dtos.sport

import com.google.cloud.Timestamp

data class SportDetailDTO(
    val id: String,
    val name: String,
    val createdAt: Timestamp,
    val updatedAt: Timestamp
)