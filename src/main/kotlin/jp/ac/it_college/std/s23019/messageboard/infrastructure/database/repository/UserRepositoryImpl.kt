package jp.ac.it_college.std.s23019.messageboard.infrastructure.database.repository

import jp.ac.it_college.std.s23019.messageboard.domain.model.Users
import jp.ac.it_college.std.s23019.messageboard.domain.repository.UserRepository
import jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao.UsersEntity
import jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao.UsersTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository


@Repository
class UserRepositoryImpl : UserRepository {
    override fun findByEmail(email: String): Users? {
        return transaction {
            UsersEntity.find {
                UsersTable.email eq email
            }.singleOrNull()?.toUser()
        }
    }

    override fun findById(id: Long): Users? {
        return transaction {
            UsersEntity.findById(id)?.toUser()
        }
    }

    override fun createUser(user: Users): Users {
        return transaction {
            val newUserEntity = UsersEntity.new {
                this.viewName = user.viewName
                this.email = user.email
                this.password = user.password
            }
            newUserEntity.toUser()
        }
    }

    override fun updateUser(user: Users): Users {
        return transaction {
            val usersEntity = UsersEntity.findById(user.id)
                ?: throw IllegalArgumentException("User not found with id: ${user.id}")

            usersEntity.apply {
                viewName = user.viewName
                email = user.email
            }
            usersEntity.toUser()
        }
    }

    override fun deleteUser(id: Long) {
        transaction {
            UsersEntity.findById(id)?.delete()
        }
    }
}