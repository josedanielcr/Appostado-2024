package com.ucenfotec.appostado.core.application.common.exceptions.team

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class TeamNotFoundException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "TeamNotFound",
    message = "Team not found",
    status = HttpStatus.NOT_FOUND,
    additionalDetails = additionalDetails
)