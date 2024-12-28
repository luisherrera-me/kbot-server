package com.kuby.kbot.entities.sensorData

import java.time.LocalDateTime


data class SensorDataDTO(
    val id: Long,
    val sensorId: Long,
    val robotId: Long,
    val value: Float,
    val latitude: Float,
    val longitude: Float,
    val timestamp: LocalDateTime?= LocalDateTime.now()
)