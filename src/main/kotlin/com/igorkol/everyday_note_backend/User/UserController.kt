package com.igorkol.everyday_note_backend.User

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/create")
    fun createUser(@RequestBody user: User): User {
        return userService.createUser(user)
    }

    @GetMapping("/get/{id}")
    fun getUser(@PathVariable id: String): User {
        return userService.findUser(id) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "User Not Found"
        )
    }

}
