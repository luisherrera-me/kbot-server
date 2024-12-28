package com.kuby.kbot.services.impl

import com.kuby.kbot.entities.Sensor
import com.kuby.kbot.repository.sensor.SensorRepository
import com.kuby.kbot.services.SensorService
import org.springframework.stereotype.Service
import java.util.*

@Service
class SensorServiceImpl(private val sensorRepository: SensorRepository) : SensorService {

    override fun findAll(): List<Sensor> {
        return sensorRepository.findAll()
    }

    override fun findById(id: Long): Sensor? {
        return sensorRepository.findById(id).orElse(null)
    }

    override fun save(sensor: Sensor): Sensor {
        return sensorRepository.save(sensor)
    }

    override fun update(id: Long, sensor: Sensor): Sensor {
        TODO("Not yet implemented")
    }


    override fun delete(id: Long) {
        if (sensorRepository.existsById(id)) {
            sensorRepository.deleteById(id)
        } else {
            throw Exception("Sensor not found")
        }
    }
}