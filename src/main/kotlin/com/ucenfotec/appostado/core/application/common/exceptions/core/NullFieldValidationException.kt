package com.ucenfotec.appostado.core.application.common.exceptions.core

import org.springframework.http.HttpStatus

class NullFieldValidationException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "NullFieldValidation",
    message = "Field value cannot be null",
    status = HttpStatus.BAD_REQUEST,
    additionalDetails = additionalDetails
)