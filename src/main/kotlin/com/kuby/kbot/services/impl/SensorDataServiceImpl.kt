package com.kuby.kbot.services.impl

import com.kuby.kbot.entities.sensorData.SensorData
import com.kuby.kbot.entities.sensorData.SensorDataDTO
import com.kuby.kbot.repository.sensor.SensorDataRepository
import com.kuby.kbot.services.SensorDataService
import com.kuby.kbot.streams.KafkaPublisherService
import org.springframework.stereotype.Service


@Service
class SensorDataServiceImpl(
    private val sensorDataRepository: SensorDataRepository,
    private val kafkaProducerService: KafkaPublisherService<SensorDataDTO>
) : SensorDataService {

    override fun findAll(): List<SensorData> {
        return sensorDataRepository.findAll()
    }

    override fun findById(id: Long): SensorData? {
        return sensorDataRepository.findById(id).orElse(null)
    }

    override fun findByIdSensor(id: Long): List<SensorData?> {
        return sensorDataRepository.findByIdWithSensor(id)
    }


    override fun save(sensorData: SensorData, sensorDataRequest: SensorDataDTO): SensorData {
        val savedData = sensorDataRepository.save(sensorData)
        kafkaProducerService.publishMessage("sensor-data-topic", sensorDataRequest)
        return savedData
    }

    override fun update(id: Long, sensorData: SensorData): SensorData {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        if (sensorDataRepository.existsById(id)) {
            sensorDataRepository.deleteById(id)
        } else {
            throw Exception("SensorData not found")
        }
    }
}