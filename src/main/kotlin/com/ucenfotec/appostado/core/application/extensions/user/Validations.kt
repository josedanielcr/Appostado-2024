package com.ucenfotec.appostado.core.application.extensions.user

import com.ucenfotec.appostado.core.application.common.exceptions.user.AgeValidationException
import com.ucenfotec.appostado.core.application.common.exceptions.user.EmailFormatException
import com.ucenfotec.appostado.core.application.dtos.user.UserDto
import java.time.LocalDate
import java.time.Period
import java.util.regex.Pattern


class ValidationConstants {
    companion object {
        const val MIN_AGE = 18
        const val EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    }
}

fun validateNewUser(user : UserDto) {
    validateAgeIsOver18(user.dateOfBirth)
    validateEmailFormat(user.email)
}

private fun validateAgeIsOver18(dateOfBirth: LocalDate?) {
    val currentDate = LocalDate.now()
    val birthDate = dateOfBirth ?: throw AgeValidationException()
    val age = Period.between(birthDate, currentDate).years
    if (age < ValidationConstants.MIN_AGE) throw AgeValidationException()
}

private fun validateEmailFormat(email: String) {
    val emailPattern = Pattern.compile(ValidationConstants.EMAIL_PATTERN)
    if (!emailPattern.matcher(email).matches()) {
        throw EmailFormatException()
    }
}