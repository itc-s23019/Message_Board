package jp.ac.it_college.std.s23019.messageboard.application.service.security

import jp.ac.it_college.std.s23019.messageboard.application.service.AuthenticationService
import jp.ac.it_college.std.s23019.messageboard.domain.model.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class MessageBoardUserDetailsService(
    private val authenticationService: AuthenticationService
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        username ?: throw UsernameNotFoundException("メールアドレスが空です")

        val user = authenticationService.findUser(username)
            ?: throw UsernameNotFoundException("${username} に該当するユーザーはいません")
        return MessageBoardUserDetails(user)
    }

    data class MessageBoardUserDetails(
        val id: Long,
        val viewName: String,
        val email: String,
        private val passwordValue: String  // プロパティ名を変更
    ) : UserDetails {
        constructor(user: Users) : this(user.id, user.viewName, user.email, user.password)

        override fun getAuthorities(): Collection<GrantedAuthority> {
            return AuthorityUtils.createAuthorityList("USER")
        }

        override fun getPassword(): String {
            return passwordValue
        }

        override fun getUsername(): String {
            return email
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
    }
}
