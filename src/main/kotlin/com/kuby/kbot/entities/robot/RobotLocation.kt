package com.kuby.kbot.entities.robot

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "robot_location")
data class RobotLocation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_robot", nullable = false)
    val robot: Robot,

    @Column(nullable = false)
    val latitude: Float,

    @Column(nullable = false)
    val longitude: Float,

    @Column(name = "timestamp", nullable = false)
    val timestamp: LocalDateTime = LocalDateTime.now()
)
