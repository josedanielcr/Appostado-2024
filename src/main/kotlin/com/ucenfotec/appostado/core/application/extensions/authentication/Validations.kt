package com.ucenfotec.appostado.core.application.extensions.authentication

import com.ucenfotec.appostado.core.application.common.exceptions.core.NullFieldValidationException
import com.ucenfotec.appostado.core.application.common.exceptions.authentication.PasswordComplexityException
import com.ucenfotec.appostado.core.application.common.exceptions.user.UserAlreadyExistsException
import com.ucenfotec.appostado.core.application.dtos.user.UserDto
import com.ucenfotec.appostado.infrastructure.repositories.user.IUserRepository
import java.util.regex.Pattern


class ValidationConstants{
    companion object {
        const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{12,}$"
        const val EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    }
}

fun validateSignUpUser(user : UserDto, userRepository: IUserRepository) {
    checkIfUserExists(user.email, userRepository)
    validateNotNullFields(user)
    validatePasswordComplexity(user.password)
}

fun checkIfUserExists(email: String, userRepository: IUserRepository) {
    try {
        val user = userRepository.getUserByEmail(email).join();
        if (user != null) throw UserAlreadyExistsException()
    } catch (e : Exception){
        return;
    }

}

private fun validateNotNullFields(user : UserDto) {
    if (user.name === "") throw NullFieldValidationException()
    if (user.surname === "") throw NullFieldValidationException()
    if (user.email === "") throw NullFieldValidationException()
    if (user.password === "") throw NullFieldValidationException()
}
private fun validatePasswordComplexity(password: String) {
    val passwordPattern = Pattern.compile(ValidationConstants.PASSWORD_PATTERN)
    if (!passwordPattern.matcher(password).matches()) {
        throw PasswordComplexityException()
    }
}