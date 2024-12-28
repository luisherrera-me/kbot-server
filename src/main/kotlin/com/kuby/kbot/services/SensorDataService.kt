package com.kuby.kbot.services

import com.kuby.kbot.entities.sensorData.SensorData
import com.kuby.kbot.entities.sensorData.SensorDataDTO

interface SensorDataService {
    fun findAll(): List<SensorData>
    fun findById(id: Long):SensorData?
    fun findByIdSensor(sensor: Long): List<SensorData?>
    fun save(sensorData: SensorData, sensorDataRequest: SensorDataDTO): SensorData
    fun update(id: Long, sensorData: SensorData): SensorData
    fun delete(id: Long)
}