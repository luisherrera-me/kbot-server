package com.kuby.kbot.streams

import com.kuby.kbot.entities.sensorData.SensorData
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaPublisherService<T>(
    private val kafkaTemplate: KafkaTemplate<String, T> // KafkaTemplate genérico
) {

    fun publishMessage(topic: String, message: T) {
        kafkaTemplate.send(topic, message)  // Publicamos el mensaje al tópico
    }
}