package com.kuby.kbot.services

import com.kuby.kbot.entities.Sensor
import java.util.*

interface SensorService {
    fun findAll(): List<Sensor>
    fun findById(id: Long): Sensor?
    fun save(sensor: Sensor): Sensor
    fun update(id: Long, sensor: Sensor): Sensor
    fun delete(id: Long)
}