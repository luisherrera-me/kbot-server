package com.kuby.kbot.services

import com.kuby.kbot.entities.robot.RobotLocation
import java.util.*

interface RobotLocationService {
    fun findAll(): List<RobotLocation>
    fun findById(id: Long): Optional<RobotLocation>
    fun save(location: RobotLocation): RobotLocation
    fun update(id: Long, location: RobotLocation): RobotLocation
    fun delete(id: Long)
}