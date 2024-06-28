package jp.ac.it_college.std.s23019.messageboard.application.security

import jp.ac.it_college.std.s23019.messageboard.domain.model.Users
import jp.ac.it_college.std.s23019.messageboard.domain.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
    private val userRepository: UserRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user: Users = userRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User not found with email: $username")

        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password, // Make sure this is the hashed password from database
            mapRolesToAuthorities(user) // Assign roles here based on your application logic
        )
    }

    private fun mapRolesToAuthorities(user: Users): Collection<GrantedAuthority> {
        val authorities: MutableList<GrantedAuthority> = mutableListOf()

        // Example: Assigning ROLE_USER statically
        authorities.add(SimpleGrantedAuthority("ROLE_USER"))

        // Example: Assigning additional roles dynamically based on user attributes
        // if (user.isAdmin) {
        //     authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        // }

        return authorities
    }
}
