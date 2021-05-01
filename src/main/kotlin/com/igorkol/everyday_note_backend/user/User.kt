package com.igorkol.everyday_note_backend.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var userName: String,
    var email: String,
    var password: String,
    var isActive: Boolean
)
