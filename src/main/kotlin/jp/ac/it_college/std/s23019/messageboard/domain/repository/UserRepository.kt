package jp.ac.it_college.std.s23019.messageboard.domain.repository

import jp.ac.it_college.std.s23019.messageboard.domain.model.Users

interface UserRepository {
    fun findByEmail(email: String): Users?

    fun findById(id: Long): Users?
}