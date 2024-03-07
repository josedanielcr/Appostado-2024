package com.ucenfotec.appostado.core.application.dtos.team

import com.google.cloud.Timestamp

data class TeamDetailDTO(
    val id: String,
    val name: String,
    val photo: String,
    val sportId: String,
    val createdAt: Timestamp,
    val updatedAt: Timestamp,
    val status : String
)