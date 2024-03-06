package com.ucenfotec.appostado.core.application.common.exceptions.user

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class EmailFormatException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "EmailFormat",
    message = "Invalid email format",
    status = HttpStatus.BAD_REQUEST,
    additionalDetails = additionalDetails
)