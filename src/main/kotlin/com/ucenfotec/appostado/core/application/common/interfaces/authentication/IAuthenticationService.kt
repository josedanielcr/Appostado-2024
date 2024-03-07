package com.ucenfotec.appostado.core.application.common.interfaces.authentication

import com.ucenfotec.appostado.core.application.dtos.user.*
import java.util.concurrent.CompletableFuture

interface IAuthenticationService {
    fun signUp(user : UserDto): CompletableFuture<UserDetailDto>
    fun signIn(user : UserLoginDto): CompletableFuture<UserLoginResultDto>
    fun updatePassword(user : UpdateUserPasswordDto): CompletableFuture<Boolean>
    fun deleteUser(userId : String): CompletableFuture<Boolean>
}