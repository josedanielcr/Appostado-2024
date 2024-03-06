package com.ucenfotec.appostado.core.application.common.exceptions.authentication

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class InvalidTokenProvidedException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "InvalidTokenProvided",
    message = "Invalid token provided",
    status = HttpStatus.UNAUTHORIZED,
    additionalDetails = additionalDetails
)