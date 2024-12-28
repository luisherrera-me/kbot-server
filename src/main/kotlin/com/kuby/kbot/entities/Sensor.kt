package com.kuby.kbot.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "sensor")
data class Sensor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 100, nullable = false)
    val name: String,

    @Column(length = 50)
    val type: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
