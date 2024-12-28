package com.kuby.kbot.repository.sensor

import com.kuby.kbot.entities.Sensor
import com.kuby.kbot.entities.sensorData.SensorData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SensorRepository : JpaRepository<Sensor, Long>{
}
