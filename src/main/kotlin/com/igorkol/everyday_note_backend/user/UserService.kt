package com.igorkol.everyday_note_backend.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userInMemoryRepository: UserInMemoryRepository

    fun createUser(user: User): User {
        userInMemoryRepository.createUser(user)
        return user
    }

    fun findUser(id: String): User? {
        return userInMemoryRepository.getUser(id)
    }
}
