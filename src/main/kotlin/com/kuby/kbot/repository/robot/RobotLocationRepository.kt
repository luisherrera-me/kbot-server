package com.kuby.kbot.repository.robot

import com.kuby.kbot.entities.robot.RobotLocation
import org.springframework.data.jpa.repository.JpaRepository

interface RobotLocationRepository : JpaRepository<RobotLocation, Long>
