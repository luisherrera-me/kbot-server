package com.kuby.kbot.controllers

import com.kuby.kbot.entities.robot.Robot
import com.kuby.kbot.entities.robot.RobotReques
import com.kuby.kbot.entities.robot.RobotSignIn
import com.kuby.kbot.services.RobotService
import com.kuby.kbot.services.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/authRobot")
class AuthRobotController (
    private val robotService: RobotService,
    private val usuarioService: UsuarioService

) {
    @PostMapping("/login")
    fun login(@RequestBody robotSignIn: RobotSignIn): ResponseEntity<Any> {
        return try {
            val token = robotService.signInRobot(robotSignIn)
            ResponseEntity.ok(mapOf("token" to token))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }

    @PostMapping("/create")
    fun createUser(@RequestBody robotReques: RobotReques): ResponseEntity<Any> {
        return try {
            robotService.findBySN(robotReques.serialNumber)?.let {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf(
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "message" to "Robot ya está registrado N/S: ${robotReques.serialNumber}",
                    "errorCode" to "N/S_ALREADY_EXISTS"
                ))
            }

            val userRequest = usuarioService.findById(robotReques.usuarioId)
                ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf(
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "message" to "Usuario no encontrado con id: ${robotReques.usuarioId}",
                    "errorCode" to "USER_NOT_FOUND"
                ))

            val robot = Robot(
                usuario = userRequest,
                name = robotReques.name,
                model = robotReques.model,
                serialNumber = robotReques.serialNumber,
                description = robotReques.description,
                createdAt = LocalDateTime.now()
            )
            val savedUser = robotService.save(robot)

            ResponseEntity.status(HttpStatus.CREATED).body(mapOf(
                "status" to HttpStatus.CREATED.value(),
                "message" to "Usuario creado exitosamente.",
                "data" to savedUser
            ))
        } catch (e: Exception) {
            handleError(e, "Error al crear el usuario")
        }
    }

    private fun handleError(e: Exception, customMessage: String): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf(
            "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(), // Código de estado HTTP
            "message" to customMessage,
            "errorCode" to "INTERNAL_SERVER_ERROR",
            "details" to e.localizedMessage
        ))
    }

    private fun errorResponse(status: HttpStatus, message: String, errorCode: String): ResponseEntity<Any> {
        return ResponseEntity.status(status).body(
            mapOf(
                "status" to status.value(),
                "message" to message,
                "errorCode" to errorCode
            )
        )
    }
}