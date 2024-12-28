package com.kuby.kbot.entities.sensorData

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kuby.kbot.entities.Sensor
import com.kuby.kbot.entities.robot.Robot
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "robot_sensor")
data class RobotSensor (
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

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,
)
