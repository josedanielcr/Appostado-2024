package com.ucenfotec.appostado.core.application.common.exceptions.authentication

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class PasswordsDoNotMatchException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "PasswordsDoNotMatchException",
    message = "The new password and the confirmation password do not match",
    status = HttpStatus.BAD_REQUEST,
    additionalDetails = additionalDetails
)