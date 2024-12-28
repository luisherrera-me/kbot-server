package com.kuby.kbot.services

import com.kuby.kbot.entities.robot.Robot
import com.kuby.kbot.entities.robot.RobotSignIn
import java.util.*

interface RobotService {
    fun findAll(): List<Robot>
    fun findById(id: Long):Robot?
    fun findBySN(sn: String): Robot?
    fun signInRobot(robotSignIn: RobotSignIn): String
    fun save(robot: Robot): Robot
    fun update(id: Long, robot: Robot): Robot
    fun delete(id: Long)
}