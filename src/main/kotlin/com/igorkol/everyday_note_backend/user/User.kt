package com.igorkol.everyday_note_backend.user

import com.igorkol.everyday_note_backend.role.Role
import javax.persistence.*

@Entity
@Table(uniqueConstraints = [
    UniqueConstraint(columnNames = ["userName"]),
    UniqueConstraint(columnNames = ["email"])
])
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var userName: String,
    var email: String,
    var password: String,
    var isActive: Boolean,
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",joinColumns = [JoinColumn(name = "user_id")],inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles:MutableSet<Role>
)
