package com.kuby.kbot.services

import com.kuby.kbot.entities.Rol
import java.util.*

interface RolService {
    fun findAll(): List<Rol>
    fun findById(id: Long): Optional<Rol>
    fun save(rol: Rol): Rol
    fun update(id: Long, rol: Rol): Rol
    fun delete(id: Long)
}