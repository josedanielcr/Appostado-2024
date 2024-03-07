package com.ucenfotec.appostado.core.application.common.exceptions.sport

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class SportNotFoundException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "SportNotFound",
    message = "Sport not found",
    status = HttpStatus.NOT_FOUND,
    additionalDetails = additionalDetails
)