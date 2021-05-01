package com.igorkol.everyday_note_backend.user

import org.springframework.stereotype.Component

@Component
class UserInMemoryRepository() {

    val users = mutableListOf<User>()

    fun createUser(user:User){
        if (!users.contains(user)){
            users.add(user)
        }
    }

    fun getUser(id: String): User? {
        return users.find { it.id == id }
    }

}
