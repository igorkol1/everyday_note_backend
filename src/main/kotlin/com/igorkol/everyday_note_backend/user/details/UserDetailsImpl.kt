package com.igorkol.everyday_note_backend.user.details

import com.fasterxml.jackson.annotation.JsonIgnore
import com.igorkol.everyday_note_backend.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors


class UserDetailsImpl(
    id: Long,
    userName: String,
    email: String,
    password: String,
    authorities: List<GrantedAuthority>
) :UserDetails {
    private val serialVersionUID = 1L

    private var id: Long? = null

    private var username: String? = null

    private var email: String? = null

    @JsonIgnore
    private var password: String? = null

    private var authorities: Collection<GrantedAuthority>? = null

    companion object {
        fun build(user: User):UserDetailsImpl {
            val authorities: List<GrantedAuthority> = user.roles.stream()
                .map { (_, name) ->
                    SimpleGrantedAuthority(
                        name.name
                    )
                }
                .collect(Collectors.toList())
            return UserDetailsImpl(
                user.id,
                user.userName,
                user.email,
                user.password,
                authorities
            )
        }
    }

    override fun getAuthorities(): Collection<GrantedAuthority>? {
        return authorities
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val user = o as UserDetailsImpl
        return Objects.equals(id, user.id)
    }
}
