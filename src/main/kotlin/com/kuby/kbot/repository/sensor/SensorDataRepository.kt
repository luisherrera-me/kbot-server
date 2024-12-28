package com.kuby.kbot.repository.sensor

import com.kuby.kbot.entities.sensorData.SensorData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SensorDataRepository : JpaRepository<SensorData, Long> {
    @Query("SELECT sd FROM SensorData sd JOIN FETCH sd.sensor WHERE sd.sensor.id = :id")
    fun findByIdWithSensor(@Param("id") id: Long?): List<SensorData?>
}
