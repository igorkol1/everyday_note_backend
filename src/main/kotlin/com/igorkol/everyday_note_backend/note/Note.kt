package com.igorkol.everyday_note_backend.note

import com.igorkol.everyday_note_backend.user.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Note(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var text: String,
    var updateDate: LocalDateTime,
    @ManyToOne() var user: User
)
