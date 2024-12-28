package com.kuby.kbot.services

import com.kuby.kbot.entities.Usuario
import java.util.*

interface UsuarioService {
    fun findAll(): List<Usuario>
    fun findById(id: Long): Usuario?
    fun save(usuario: Usuario): Usuario
    fun update(id: Long, usuario: Usuario): Usuario
    fun delete(id: Long)
}