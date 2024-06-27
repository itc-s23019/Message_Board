package jp.ac.it_college.std.s23019.messageboard.application.service

import jp.ac.it_college.std.s23019.messageboard.domain.model.Messages
import jp.ac.it_college.std.s23019.messageboard.domain.repository.MessageRepository
import org.springframework.stereotype.Service


@Service
class MessageService(
    private val messageRepository: MessageRepository
) {
    fun createMessage(message: Messages): Messages {
        return messageRepository.createMessage(message)
    }
    fun getAllMessages(): List<Messages> {
        return messageRepository.getAllMessages()
    }

}