package com.kuby.kbot.exception

import com.kuby.kbot.utils.ErrorResponseWriter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint(
    private val errorResponseWriter: ErrorResponseWriter
) : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        errorResponseWriter.writeErrorResponse(
            response,
            HttpStatus.UNAUTHORIZED,
            "No estás autenticado. Por favor inicia sesión."
        )
    }
}