package com.ucenfotec.appostado.api.handlers

import com.ucenfotec.appostado.api.common.ErrorResponse
import com.ucenfotec.appostado.core.application.common.exceptions.BaseCustomException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BaseCustomException::class)
    fun handleBaseCustomException(ex: BaseCustomException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            errorCode = ex.code,
            errorMessage = ex.message,
            status = ex.status.value(),
            additionalDetails = ex.additionalDetails,
            timestamp = ex.timestamp
        )
        return ResponseEntity.status(ex.status).body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            errorCode = "GenericError",
            errorMessage = ex.message ?: "A server error occurred",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            timestamp = LocalDateTime.now()
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}