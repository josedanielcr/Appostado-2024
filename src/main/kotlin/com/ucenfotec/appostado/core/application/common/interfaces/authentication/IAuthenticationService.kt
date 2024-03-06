package com.ucenfotec.appostado.core.application.common.interfaces.authentication

import com.ucenfotec.appostado.core.application.dtos.user.UserDetailDto
import com.ucenfotec.appostado.core.application.dtos.user.UserDto
import java.util.concurrent.CompletableFuture

interface IAuthenticationService {
    fun signUp(user : UserDto): CompletableFuture<UserDetailDto>
}