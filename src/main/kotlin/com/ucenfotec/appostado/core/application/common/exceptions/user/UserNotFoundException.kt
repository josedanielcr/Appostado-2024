package com.ucenfotec.appostado.core.application.common.exceptions.user

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class UserNotFoundException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "UserNotFound",
    message = "User not found",
    status = HttpStatus.NOT_FOUND,
    additionalDetails = additionalDetails
)