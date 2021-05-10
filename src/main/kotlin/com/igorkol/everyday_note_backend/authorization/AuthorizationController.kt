package com.igorkol.everyday_note_backend.authorization

import com.igorkol.everyday_note_backend.authorization.requests.LoginRequest
import com.igorkol.everyday_note_backend.authorization.requests.SignupRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/auth")
class AuthorizationController {

    @Autowired
    lateinit var authorizationService: AuthorizationService

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        val jwtResponse = authorizationService.authorize(loginRequest.username, loginRequest.password)
        return ResponseEntity.ok<Any>(
            jwtResponse
        )
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: SignupRequest): ResponseEntity<*> {
        return authorizationService.registerUser(signUpRequest)
    }
}
