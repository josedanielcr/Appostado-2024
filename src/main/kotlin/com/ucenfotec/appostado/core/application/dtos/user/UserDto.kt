package com.ucenfotec.appostado.core.application.dtos.user

import java.time.LocalDate

data class UserDto(
    val name : String,
    val surname : String,
    val email : String,
    val password : String,
    val dateOfBirth : LocalDate
)
