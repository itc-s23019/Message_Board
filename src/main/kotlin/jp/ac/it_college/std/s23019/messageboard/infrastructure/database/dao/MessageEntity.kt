package jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao

import jp.ac.it_college.std.s23019.messageboard.domain.model.Messages
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID



class MessageEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MessageEntity>(MessageTable)

    var threadId by ThreadsEntity referencedOn MessageTable.threadId
    var userId by UsersEntity referencedOn MessageTable.userId
    var message by MessageTable.message
    var postedAt by MessageTable.postedAt
    var updateAt by MessageTable.updatedAt
    var deleted by MessageTable.deleted

    fun toMessage(): Messages {
        return Messages(
            id.value,
            threadId.id.value,
            userId.id.value,
            message,
            postedAt,
            updateAt,
            deleted
        )
    }
}