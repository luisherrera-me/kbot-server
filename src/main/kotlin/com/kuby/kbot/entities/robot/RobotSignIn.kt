package com.kuby.kbot.entities.robot

data class RobotSignIn(
    val id: Long,
    val usuario: Long,
    val model: String,
    val serialNumber: String,
)