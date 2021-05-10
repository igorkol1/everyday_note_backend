package com.igorkol.everyday_note_backend.user.details

import com.igorkol.everyday_note_backend.user.User
import com.igorkol.everyday_note_backend.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserDetailsServiceImpl:UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user: User = userRepository.findByUserName(username)
            .orElseThrow { UsernameNotFoundException("User Not Found with username: $username") }
        return UserDetailsImpl.build(user)
    }

}
