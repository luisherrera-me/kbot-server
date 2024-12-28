package com.kuby.kbot.exception

import com.kuby.kbot.utils.ErrorResponseWriter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class CustomAccessDeniedHandler(
    private val errorResponseWriter: ErrorResponseWriter
) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        errorResponseWriter.writeErrorResponse(
            response,
            HttpStatus.FORBIDDEN,
            "No tienes permisos para acceder a este recurso."
        )
    }
}