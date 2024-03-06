package com.ucenfotec.appostado.core.application.common.exceptions.authentication

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class PasswordComplexityException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "PasswordComplexity",
    message = "Password must contain at least 1 digit, 1 lower case, 1 upper case, 1 symbol and be at least 12 characters long",
    status = HttpStatus.BAD_REQUEST,
    additionalDetails = additionalDetails
)