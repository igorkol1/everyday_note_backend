package com.igorkol.everyday_note_backend.authorization

import com.igorkol.everyday_note_backend.user.details.UserDetailsImpl
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtils {
    @Value("\${igorkol.everyday_note_backend.jwtSecret}")
    private val jwtSecret: String? = null

    @Value("\${igorkol.everyday_note_backend.jwtExpirationMs}")
    private val jwtExpirationMs = 0

    fun generateJwtToken(authentication: Authentication): String? {
        val userPrincipal = authentication.getPrincipal() as UserDetailsImpl
        return Jwts.builder().setSubject(userPrincipal.username).setIssuedAt(Date())
            .setExpiration(Date(Date().getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUserNameFromJwtToken(token: String?): String? {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.subject
    }

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            println("Invalid JWT signature: ${e.localizedMessage}")
        } catch (e: MalformedJwtException) {
            println("Invalid JWT token: ${e.localizedMessage}")
        } catch (e: ExpiredJwtException) {
            println("JWT token is expired: ${e.localizedMessage}")
        } catch (e: UnsupportedJwtException) {
            println("JWT token is unsupported: ${e.localizedMessage}")
        } catch (e: IllegalArgumentException) {
            println("JWT claims string is empty: ${e.localizedMessage}")
        }
        return false
    }
}
