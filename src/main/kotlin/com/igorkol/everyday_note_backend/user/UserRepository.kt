package com.igorkol.everyday_note_backend.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository:JpaRepository<User,Long>{

    fun findByUserName(userName:String): Optional<User>

    fun existsByUserName(userName: String):Boolean

    fun existsByEmail(email:String):Boolean

}
