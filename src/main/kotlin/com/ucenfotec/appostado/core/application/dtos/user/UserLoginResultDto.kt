package com.ucenfotec.appostado.core.application.dtos.user

data class UserLoginResultDto(
    val token : String,
    val user : UserDetailDto
)
