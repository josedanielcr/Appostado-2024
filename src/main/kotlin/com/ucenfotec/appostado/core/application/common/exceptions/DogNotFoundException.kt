package com.ucenfotec.appostado.core.application.common.exceptions

import org.springframework.http.HttpStatus

class DogNotFoundException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "DogNotFound",
    message = "Dog not found",
    status = HttpStatus.NOT_FOUND,
    additionalDetails = additionalDetails
)