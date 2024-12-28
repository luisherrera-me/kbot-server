package com.kuby.kbot.repository.robot

import com.kuby.kbot.entities.robot.Robot
import org.springframework.data.jpa.repository.JpaRepository

interface RobotRepository : JpaRepository<Robot, Long>{
    fun findBySerialNumber(serialNumber: String): Robot?
}
