package com.kuby.kbot.utils

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ErrorResponseWriter(private val objectMapper: ObjectMapper) {

    fun writeErrorResponse(
        response: HttpServletResponse,
        status: HttpStatus,
        message: String
    ) {
        response.status = status.value()
        response.contentType = "application/json"
        val errorResponse = mapOf(
            "status" to status.value(),
            "message" to message
        )
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}