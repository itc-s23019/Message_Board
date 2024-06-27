package jp.ac.it_college.std.s23009.messageboard.application.service


import jp.ac.it_college.std.s23019.messageboard.domain.model.Users
import jp.ac.it_college.std.s23019.messageboard.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val usersRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun register(viewName: String, email: String, password: String): Users {
        println("Registering user with email: $email")
        val hashedPassword = passwordEncoder.encode(password)
        val user = Users(
            id = 0, // 新規登録時にはIDは自動生成されるため0で初期化
            viewName,
            email,
            hashedPassword
        )
        return usersRepository.createUser(user)
    }

    @Transactional
    fun findByEmail(email: String): Users? {
        println("Finding user by email: $email")
        return usersRepository.findByEmail(email)
    }

    @Transactional
    fun delete(id: Long) {
        println("Deleting user with id: $id")
        return usersRepository.deleteUser(id)
    }
}