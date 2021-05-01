package com.igorkol.everyday_note_backend.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun createUser(user: User): User {
return userRepository.save(user)
    }

    fun findUser(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }
}
