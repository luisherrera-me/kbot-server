package com.kuby.kbot.entities.robot

import com.kuby.kbot.entities.Usuario
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime


data class RobotReques(
    val usuarioId: Long,
    val name: String,
    val model: String,
    val serialNumber: String,
    val description: String? = null,
)