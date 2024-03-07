package com.ucenfotec.appostado.core.application.dtos.user

data class UpdateUserPasswordDto (
    var userId : String = "",
    val oldPassword : String,
    val newPassword : String,
    val confirmNewPassword : String
)