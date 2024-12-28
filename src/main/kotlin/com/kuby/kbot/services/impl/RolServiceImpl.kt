package com.kuby.kbot.services.impl

import com.kuby.kbot.entities.Rol
import com.kuby.kbot.repository.user.RolRepository
import com.kuby.kbot.services.RolService
import org.springframework.stereotype.Service
import java.util.*

@Service
class RolServiceImpl(private val rolRepository: RolRepository) : RolService {

    override fun findAll(): List<Rol> {
        return rolRepository.findAll()
    }

    override fun findById(id: Long): Optional<Rol> {
        return rolRepository.findById(id)
    }

    override fun save(rol: Rol): Rol {
        return rolRepository.save(rol)
    }

    override fun update(id: Long, rol: Rol): Rol {
        TODO("Not yet implemented")
    }


    override fun delete(id: Long) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id)
        } else {
            throw Exception("Rol not found")
        }
    }
}