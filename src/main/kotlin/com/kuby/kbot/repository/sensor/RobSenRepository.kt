package com.kuby.kbot.repository.sensor

import com.kuby.kbot.entities.Sensor
import com.kuby.kbot.entities.robot.Robot
import com.kuby.kbot.entities.sensorData.RobotSensor
import com.kuby.kbot.entities.sensorData.SensorData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RobSenRepository : JpaRepository<RobotSensor, Long> {

    // 1. Listar sensores de un robot específico
    @Query("SELECT rs.sensor FROM RobotSensor rs WHERE rs.robot.id = :robotId")
    fun findSensorsByRobotId(@Param("robotId") robotId: Long): List<Sensor>

    // 2. Listar robots que usan un sensor específico
    @Query("SELECT rs.robot FROM RobotSensor rs WHERE rs.sensor.id = :sensorId")
    fun findRobotsBySensorId(@Param("sensorId") sensorId: Long): List<Robot>

    // 3. Sensores no asignados a un robot específico
    @Query("SELECT s FROM Sensor s WHERE s.id NOT IN (SELECT rs.sensor.id FROM RobotSensor rs WHERE rs.robot.id = :robotId)")
    fun findUnassignedSensorsForRobot(@Param("robotId") robotId: Long): List<Sensor>

    // 4. Robots que no tienen un sensor específico
    @Query("SELECT r FROM Robot r WHERE r.id NOT IN (SELECT rs.robot.id FROM RobotSensor rs WHERE rs.sensor.id = :sensorId)")
    fun findRobotsWithoutSensor(@Param("sensorId") sensorId: Long): List<Robot>
}
