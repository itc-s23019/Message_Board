package jp.ac.it_college.std.s23019.messageboard.infrastructure.database.repository

import jp.ac.it_college.std.s23019.messageboard.domain.model.Messages
import jp.ac.it_college.std.s23019.messageboard.domain.repository.MessageRepository
import jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao.MessageEntity
import jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao.MessageTable
import jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao.ThreadsEntity
import jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao.UsersEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class MessageRepositoryImpl : MessageRepository {
    override fun createMessage(message: Messages): Messages {
        return transaction {
            val newMessage = MessageEntity.new {
                threadId = ThreadsEntity[message.threadId]
                userId = UsersEntity[message.userId]
                this.message = message.message
                postedAt = message.postedAt
                updateAt = message.updatedAt
                deleted = message.deleted
            }
            newMessage.toMessage()
        }
    }

    override fun getAllMessages(): List<Messages> {
        return transaction {
            MessageEntity.all().map { it.toMessage() }
        }
    }

    override fun getMessageId(id: Long): Messages? {
        return transaction {
            val messageEntity = MessageEntity.findById(id)
            messageEntity?.toMessage()
        }
    }

    override fun getMessageByThreadId(threadId: Long): List<Messages> {
        return transaction {
            MessageEntity.find {
                MessageTable.threadId eq threadId
            }.map { it.toMessage() }
        }
    }

    override fun updateMessage(message: Messages): Messages {
        return transaction {
            val messageEntity = MessageEntity.findById(message.id)
                ?: throw IllegalArgumentException("更新箇所が見つかりません${message.id}")
            messageEntity.apply {
                threadId = ThreadsEntity[message.threadId]
                userId = UsersEntity[message.userId]
                this.message = message.message
                postedAt = message.postedAt
                updateAt = message.updatedAt
                deleted = message.deleted
            }

            messageEntity.toMessage()
        }
    }

    override fun deleteMessage(id: Long) {
        transaction {
            MessageEntity.findById(id)?.delete()
        }
    }
}
