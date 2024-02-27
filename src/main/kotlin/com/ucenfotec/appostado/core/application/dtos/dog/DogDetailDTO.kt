package com.ucenfotec.appostado.core.application.dtos.dog

import com.google.cloud.Timestamp
import java.time.LocalDateTime
import java.util.*

data class DogDetailDTO(
    val id: String,
    val name: String,
    val age: Int,
    val createdAt: Timestamp,
    val updatedAt: Timestamp
)