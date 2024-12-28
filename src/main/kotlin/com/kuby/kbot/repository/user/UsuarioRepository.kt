package com.kuby.kbot.repository.user

import com.kuby.kbot.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long>
