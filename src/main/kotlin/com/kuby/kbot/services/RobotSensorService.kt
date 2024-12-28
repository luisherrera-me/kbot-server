package com.kuby.kbot.services

import com.kuby.kbot.entities.Sensor
import com.kuby.kbot.entities.robot.Robot
import org.springframework.stereotype.Service


@Service
interface RobotSensorService {

    /**
     * List all sensors assigned to a specific robot.
     * @param robotId ID of the robot.
     * @return List of sensors.
     */
    fun getSensorsByRobotId(robotId: Long): List<Sensor?>

    /**
     * List all robots using a specific sensor.
     * @param sensorId ID of the sensor.
     * @return List of robots.
     */
    fun getRobotsBySensorId(sensorId: Long): List<Robot?>

    /**
     * List all sensors not assigned to a specific robot.
     * @param robotId ID of the robot.
     * @return List of unassigned sensors.
     */
    fun getUnassignedSensorsForRobot(robotId: Long): List<Sensor?>

    /**
     * List all robots that do not have a specific sensor assigned.
     * @param sensorId ID of the sensor.
     * @return List of robots.
     */
    fun getRobotsWithoutSensor(sensorId: Long): List<Robot?>
}