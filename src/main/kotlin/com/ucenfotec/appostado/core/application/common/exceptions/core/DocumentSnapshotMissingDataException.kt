package com.ucenfotec.appostado.core.application.common.exceptions.core

import com.ucenfotec.appostado.core.application.common.exceptions.core.BaseCustomException
import org.springframework.http.HttpStatus

class DocumentSnapshotMissingDataException(
    additionalDetails: Map<String, Any?>? = null
) : BaseCustomException(
    code = "DocumentSnapshotMissingData",
    message = "Document snapshot is missing data",
    status = HttpStatus.NOT_FOUND,
    additionalDetails = additionalDetails
)