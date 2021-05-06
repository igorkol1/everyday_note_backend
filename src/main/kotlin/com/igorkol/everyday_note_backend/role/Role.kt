package com.igorkol.everyday_note_backend.role

import javax.persistence.*

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    var name: RoleTypes
)
