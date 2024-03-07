package com.ucenfotec.appostado.core.application.dtos.user

import com.google.cloud.Timestamp
import java.time.LocalDate

data class UserDetailDto(
    val id : String,
    val name : String,
    val surname : String,
    val profilePictureUrl : String?,
    val email : String,
    val dateOfBirth : Timestamp,
    val createdAt : Timestamp,
    val updatedAt : Timestamp,
    val status : String,
    val role : String
)
