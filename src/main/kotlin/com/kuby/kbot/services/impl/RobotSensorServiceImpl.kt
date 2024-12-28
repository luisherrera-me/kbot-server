package com.kuby.kbot.services.impl

import com.kuby.kbot.entities.Sensor
import com.kuby.kbot.entities.robot.Robot
import com.kuby.kbot.repository.sensor.RobSenRepository
import com.kuby.kbot.services.RobotSensorService
import org.springframework.stereotype.Service


@Service
class RobotSensorServiceImpl (
    private val robSenRepository: RobSenRepository
): RobotSensorService {

    override fun getSensorsByRobotId(robotId: Long): List<Sensor?> {
        return robSenRepository.findSensorsByRobotId(robotId)
    }

    override fun getRobotsBySensorId(sensorId: Long): List<Robot?> {
        return robSenRepository.findRobotsBySensorId(sensorId)
    }

    override fun getUnassignedSensorsForRobot(robotId: Long): List<Sensor?> {
        return robSenRepository.findUnassignedSensorsForRobot(robotId)
    }

    override fun getRobotsWithoutSensor(sensorId: Long): List<Robot?> {
        return robSenRepository.findRobotsWithoutSensor(sensorId)
    }
}