package com.kuby.kbot.controllers


import com.kuby.kbot.services.RobotService
import com.kuby.kbot.services.UsuarioService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/robot")
class RobotController (
    private val robotService: RobotService,
    private val usuarioService: UsuarioService
){

}