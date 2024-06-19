package jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao

import org.jetbrains.exposed.dao.id.LongIdTable

object UsersTable : LongIdTable("users"){
    val viewName = varchar("view_name", 32)
    val email = varchar("email", 256)
    val password = varchar("pass", 128)
}