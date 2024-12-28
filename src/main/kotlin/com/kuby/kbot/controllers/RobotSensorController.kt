package com.kuby.kbot.controllers

import com.kuby.kbot.entities.Sensor
import com.kuby.kbot.entities.robot.Robot
import com.kuby.kbot.services.RobotSensorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/robot-sensors")
class RobotSensorController @Autowired constructor(
    private val robotSensorService: RobotSensorService
) {

    /**
     * Get all sensors assigned to a specific robot.
     * @param robotId ID of the robot.
     * @return List of sensors.
     */
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/robot/{robotId}/sensors")
    fun getSensorsByRobotId(@PathVariable robotId: Long): List<Sensor?> {
        return robotSensorService.getSensorsByRobotId(robotId)
    }

    /**
     * Get all robots using a specific sensor.
     * @param sensorId ID of the sensor.
     * @return List of robots.
     */
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/sensor/{sensorId}/robots")
    fun getRobotsBySensorId(@PathVariable sensorId: Long): List<Robot?> {
        return robotSensorService.getRobotsBySensorId(sensorId)
    }

    /**
     * Get all sensors not assigned to a specific robot.
     * @param robotId ID of the robot.
     * @return List of unassigned sensors.
     */
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/robot/{robotId}/unassigned-sensors")
    fun getUnassignedSensorsForRobot(@PathVariable robotId: Long): List<Sensor?> {
        return robotSensorService.getUnassignedSensorsForRobot(robotId)
    }

    /**
     * Get all robots that do not have a specific sensor assigned.
     * @param sensorId ID of the sensor.
     * @return List of robots.
     */
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/sensor/{sensorId}/unassigned-robots")
    fun getRobotsWithoutSensor(@PathVariable sensorId: Long): List<Robot?> {
        return robotSensorService.getRobotsWithoutSensor(sensorId)
    }
}