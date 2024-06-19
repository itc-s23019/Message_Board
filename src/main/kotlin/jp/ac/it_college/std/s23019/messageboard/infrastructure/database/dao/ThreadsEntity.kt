package jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ThreadsEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ThreadsEntity>(ThreadsTable)

    var title by ThreadsTable.title
    var userId by ThreadsTable.userId
    var createdAt by ThreadsTable.createdAt
    var updatedAt by ThreadsTable.updatedAt
    var deleted by ThreadsTable.deleted
}