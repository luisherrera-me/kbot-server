package com.kuby.kbot.services.impl

import com.kuby.kbot.entities.Usuario
import com.kuby.kbot.repository.user.UsuarioRepository
import com.kuby.kbot.services.UsuarioService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioServiceImpl(private val usuarioRepository: UsuarioRepository) : UsuarioService {

    override fun findAll(): List<Usuario> {
        return usuarioRepository.findAll()
    }

    override fun findById(id: Long): Usuario? {
        return usuarioRepository.findById(id).orElse(null)
    }

    override fun save(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    override fun update(id: Long, usuario: Usuario): Usuario {
        TODO("Not yet implemented")
    }


    override fun delete(id: Long) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id)
        } else {
            throw Exception("Usuario not found")
        }
    }
}