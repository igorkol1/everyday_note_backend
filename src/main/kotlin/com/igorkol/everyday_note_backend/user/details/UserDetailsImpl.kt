package com.igorkol.everyday_note_backend.user.details

import com.igorkol.everyday_note_backend.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors


data class UserDetailsImpl(
    var id: Long,
    val userName: String,
    var email: String,
    private var password: String,
    var authorities: List<GrantedAuthority>
) :UserDetails {

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

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        val value = userName
        return value
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
