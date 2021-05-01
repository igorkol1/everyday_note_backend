package com.igorkol.everyday_note_backend.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User,Long>
