package jp.ac.it_college.std.s23019.messageboard.application.service

import jp.ac.it_college.std.s23019.messageboard.domain.model.Users
import jp.ac.it_college.std.s23019.messageboard.domain.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class UserService (
    private val userRepository: UserRepository
){
    fun createUser(user: Users): Users{
        return userRepository.createUser(user)
    }
}