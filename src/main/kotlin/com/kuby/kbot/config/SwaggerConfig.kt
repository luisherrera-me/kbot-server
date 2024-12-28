package com.kuby.kbot.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("API REST - Ejemplo con Kotlin y Spring Boot")
                    .version("1.0.0")
                    .description("Documentación generada automáticamente usando Swagger OpenAPI para una API REST")
            )
    }
}