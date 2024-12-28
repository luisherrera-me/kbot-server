package com.kuby.kbot.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import com.kuby.kbot.entities.sensorData.SensorData
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
@EnableKafka
class KafkaConfig {
    @Bean
    fun <T> producerFactory(): ProducerFactory<String, T> {
        val configProps = mapOf(
            "bootstrap.servers" to "localhost:9092",
            "key.serializer" to StringSerializer::class.java,
            "value.serializer" to JsonSerializer::class.java
        )
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun <T> kafkaTemplate(): KafkaTemplate<String, T> {
        return KafkaTemplate(producerFactory<T>())
    }
}