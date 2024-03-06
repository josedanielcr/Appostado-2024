package com.ucenfotec.appostado.core.application.common.exceptions.user

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class AgeValidationException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "AgeValidation",
    message = "User must be at least 18 years old",
    status = HttpStatus.BAD_REQUEST,
    additionalDetails = additionalDetails
)