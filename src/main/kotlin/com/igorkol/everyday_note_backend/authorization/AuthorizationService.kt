package com.igorkol.everyday_note_backend.authorization

import com.igorkol.everyday_note_backend.authorization.requests.JwtResponse
import com.igorkol.everyday_note_backend.authorization.requests.MessageResponse
import com.igorkol.everyday_note_backend.authorization.requests.SignupRequest
import com.igorkol.everyday_note_backend.role.Role
import com.igorkol.everyday_note_backend.role.RoleRepository
import com.igorkol.everyday_note_backend.role.RoleTypes
import com.igorkol.everyday_note_backend.user.User
import com.igorkol.everyday_note_backend.user.UserRepository
import com.igorkol.everyday_note_backend.user.details.UserDetailsImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.function.Consumer
import java.util.stream.Collectors

@Service
class AuthorizationService {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var encoder: PasswordEncoder

    @Autowired
    lateinit var jwtUtils: JwtUtils

    fun authorize(userName:String,password:String): JwtResponse {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(userName, password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils.generateJwtToken(authentication)
        val userDetails = authentication.getPrincipal() as UserDetailsImpl
        val roles = userDetails.authorities.stream()
            .map { item: GrantedAuthority -> item.authority }
            .collect(Collectors.toList())
        return             JwtResponse(
            jwt!!,
            userDetails.id,
            userDetails.username,
            userDetails.email,
            roles
        )
    }

    fun registerUser(signUpRequest: SignupRequest): ResponseEntity<Any> {
        if (userRepository.existsByUserName(signUpRequest.username)) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Username ${signUpRequest.username} is already taken!"))
        }
        if (userRepository.existsByEmail(signUpRequest.email)) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Email ${signUpRequest.email} is already in use!"))
        }

        // Create new user's account
        val user = User(
            0L,
            signUpRequest.username,
            signUpRequest.email,
            encoder.encode(signUpRequest.password),
            true,
            mutableSetOf()
        )
        val strRoles: Set<String> = signUpRequest.role
        val roles: MutableSet<Role> = HashSet<Role>()
        strRoles.forEach(Consumer { role: String? ->
            when (role) {
                "admin" -> {
                    val adminRole: Role = roleRepository.findByName(RoleTypes.ROLE_ADMIN)
                        .orElseThrow {
                            RuntimeException(
                                "Error: Role is not found."
                            )
                        }
                    roles.add(adminRole)
                }
                else -> {
                    val userRole: Role = roleRepository.findByName(RoleTypes.ROLE_USER)
                        .orElseThrow {
                            RuntimeException(
                                "Error: Role is not found."
                            )
                        }
                    roles.add(userRole)
                }
            }
        })
        user.roles=roles
        userRepository.save<User>(user)
        return ResponseEntity.ok(MessageResponse("User registered successfully!"))
    }

}
