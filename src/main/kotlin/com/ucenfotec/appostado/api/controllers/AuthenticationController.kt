package com.ucenfotec.appostado.api.controllers

import com.ucenfotec.appostado.core.application.common.interfaces.authentication.IAuthenticationService
import com.ucenfotec.appostado.core.application.dtos.user.UpdateUserPasswordDto
import com.ucenfotec.appostado.core.application.dtos.user.UserDto
import com.ucenfotec.appostado.core.application.dtos.user.UserLoginDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class AuthenticationController(
    val authenticationService: IAuthenticationService
) {
    @PostMapping("/auth/sign-up")
    fun signUp(@RequestBody user: UserDto): ResponseEntity<Any> {
        val createdUser = authenticationService.signUp(user);
        val createdResult = createdUser.get();
        return ResponseEntity.ok(createdResult);
    }

    @PostMapping("/auth/sign-in")
    fun signIn(@RequestBody user: UserLoginDto): ResponseEntity<Any> {
        val signedUser = authenticationService.signIn(user);
        val signedResult = signedUser.get();
        return ResponseEntity.ok(signedResult);
    }

    @PutMapping("/user/{userId}/update-password")
    fun updatePassword(@RequestBody user: UpdateUserPasswordDto, @PathVariable userId : String):
            ResponseEntity<Any> {
        user.userId = userId;
        val updatedUser = authenticationService.updatePassword(user);
        val updatedResult = updatedUser.get();
        return ResponseEntity.ok(updatedResult);
    }
}