package com.ucenfotec.appostado.core.application.common.exceptions.user

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class UserAlreadyExistsException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "UserAlreadyExists",
    message = "User already exists",
    status = HttpStatus.BAD_REQUEST,
    additionalDetails = additionalDetails
)