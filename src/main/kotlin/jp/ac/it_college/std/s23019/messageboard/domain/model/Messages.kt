package jp.ac.it_college.std.s23019.messageboard.domain.model

import kotlinx.datetime.LocalDateTime

data class Messages(
    val id: Long,
    val threadId: Long,
    val userId: Long,
    val message: String,
    val postedAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deleted: Boolean
)