package com.kuby.kbot.repository.user

import com.kuby.kbot.entities.Rol
import org.springframework.data.jpa.repository.JpaRepository

interface RolRepository : JpaRepository<Rol, Long>
