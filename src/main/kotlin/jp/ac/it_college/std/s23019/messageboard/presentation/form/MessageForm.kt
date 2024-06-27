package jp.ac.it_college.std.s23019.messageboard.presentation.form

import jp.ac.it_college.std.s23019.messageboard.domain.model.Messages
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class MessageResponse(
    val id: Long,
    val threadId: Long,
    val userId: Long,
    val message: String,
    val postedAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deleted: Boolean
) {
    companion object {
        fun fromModel(message: Messages): MessageResponse {
            return MessageResponse(
                id = message.id,
                threadId = message.threadId,
                userId = message.userId,
                message = message.message,
                postedAt = message.postedAt,
                updatedAt = message.updatedAt,
                deleted = message.deleted
            )
        }
    }
}

data class CreateMessageRequest(
    val threadId: Long,
    val userId: Long,
    val message: String
) {
    fun toModel(): Messages {
        return Messages(
            id = 0L, // データベースで自動生成されることを仮定しています
            threadId = threadId,
            userId = userId,
            message = message,
            postedAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
            deleted = false
        )
    }
}

