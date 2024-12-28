package com.kuby.kbot.security

import com.kuby.kbot.entities.robot.Robot
import com.kuby.kbot.entities.Usuario
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    private val secret = "1maoY5W9R-hJLwoRZqEjNaViYTC8hsOXty137UjRhR0=\n"
    private val expiration = 86400000 // 1 día en milisegundos

    fun generateTokenForEntity(entity: Any): String {
        val claims = mutableMapOf<String, Any>()
        val subject: String

        when (entity) {
            is Usuario -> {
                subject = entity.email
                claims["roles"] = listOf("ROLE_${entity.rol.name}")
                claims["type"] = "user"
                claims["userId"] = entity.id
            }
            is Robot -> {
                subject = entity.serialNumber
                claims["roles"] = listOf("ROLE_DEVICE")
                claims["ownerId"] = entity.usuario.id
                claims["model"] = entity.model
                claims["robotId"] = entity.id
            }
            else -> throw IllegalArgumentException("Unsupported entity type: ${entity::class.simpleName}")
        }

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS256, secret.toByteArray())
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = extractAllClaims(token)
            !isTokenExpired(claims)
        } catch (e: Exception) {
            false
        }
    }

    fun extractUsername(token: String): String {
        return extractAllClaims(token).subject
    }

    fun extractRoles(token: String): List<String> {
        val claims = extractAllClaims(token)
        return claims["roles"] as List<String>
    }

    fun extractType(token: String): String {
        val claims = extractAllClaims(token)
        return claims["type"] as String
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secret.toByteArray())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(claims: Claims): Boolean {
        return claims.expiration.before(Date())
    }
}
