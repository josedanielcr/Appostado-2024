package com.ucenfotec.appostado.core.application.common.exceptions.authentication

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class NoTokenProvidedException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "NoTokenProvided",
    message = "No token provided",
    status = HttpStatus.UNAUTHORIZED,
    additionalDetails = additionalDetails
)