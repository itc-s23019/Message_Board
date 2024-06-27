package jp.ac.it_college.std.s23019.messageboard.domain.repository

import jp.ac.it_college.std.s23019.messageboard.domain.model.Messages


interface MessageRepository {
    fun createMessage(message: Messages): Messages

    fun getAllMessages(): List<Messages>

    fun getMessageId(id: Long): Messages?

    fun getMessageByThreadId(threadId: Long): List<Messages>

    fun updateMessage(message: Messages): Messages

    fun deleteMessage(id: Long)

}