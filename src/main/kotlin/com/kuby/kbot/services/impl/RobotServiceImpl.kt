package com.kuby.kbot.services.impl

import com.kuby.kbot.entities.robot.Robot
import com.kuby.kbot.entities.robot.RobotSignIn
import com.kuby.kbot.repository.robot.RobotRepository
import com.kuby.kbot.repository.user.UsuarioRepository
import com.kuby.kbot.security.JwtUtil
import com.kuby.kbot.services.RobotService
import org.springframework.stereotype.Service
import java.util.*

@Service
class RobotServiceImpl(
    private val robotRepository: RobotRepository,
    private val usuarioRepository: UsuarioRepository,
    private val jwtUtil: JwtUtil,
) : RobotService {

    override fun findAll(): List<Robot> {
        return robotRepository.findAll()
    }

    override fun findById(id: Long): Robot {
        return robotRepository.findById(id).orElse(null)
    }

    override fun findBySN(sn: String): Robot? {
        return robotRepository.findBySerialNumber(sn)
    }

    override fun signInRobot(robotSignIn: RobotSignIn): String {
        val robot = robotRepository.findBySerialNumber(robotSignIn.serialNumber)
            ?: throw Exception("Robot no encontrado")

        if (robotSignIn.serialNumber!= robot.serialNumber) {
            throw Exception("Credenciales no validad")
        }
        val userResponse = usuarioRepository.findById(robotSignIn.usuario).orElseThrow { RuntimeException("Role not found") }
        if (userResponse!= robot.usuario) {
            throw Exception("Credenciales no validad")
        }
        return jwtUtil.generateTokenForEntity(robot)
    }


    override fun save(robot: Robot): Robot {
        return robotRepository.save(robot)
    }

    override fun update(id: Long, robot: Robot): Robot {
        TODO("Not yet implemented")
    }


    override fun delete(id: Long) {
        if (robotRepository.existsById(id)) {
            robotRepository.deleteById(id)
        } else {
            throw Exception("Robot not found")
        }
    }
}