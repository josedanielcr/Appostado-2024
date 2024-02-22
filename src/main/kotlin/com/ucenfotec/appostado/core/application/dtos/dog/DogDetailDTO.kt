package com.ucenfotec.appostado.core.application.dtos.dog

import java.time.LocalDateTime
import java.util.*

data class DogDetailDTO(
    val id: UUID,
    val name: String?,
    val age: Int?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)