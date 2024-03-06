package com.ucenfotec.appostado.core.application.common.exceptions.authentication

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class InvalidPasswordException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "InvalidPasswordException",
    message = "The provided password is invalid.",
    status = HttpStatus.UNAUTHORIZED,
    additionalDetails = additionalDetails
)