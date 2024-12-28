package com.kuby.kbot.entities.sensorData

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.kuby.kbot.entities.Sensor
import com.kuby.kbot.entities.robot.Robot
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "sensor_data")
data class SensorData(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_sensor", nullable = false)
    val sensor: Sensor,

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_robot", nullable = false)
    val robot: Robot,

    @Column(nullable = false)
    val value: Float,

    @Column(nullable = false)
    val latitude: Float,

    @Column(nullable = false)
    val longitude: Float,

    @Column(name = "timestamp", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val timestamp: LocalDateTime = LocalDateTime.now()
)

