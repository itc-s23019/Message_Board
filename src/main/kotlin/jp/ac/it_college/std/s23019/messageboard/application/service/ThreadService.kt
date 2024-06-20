package jp.ac.it_college.std.s23019.messageboard.application.service

import jp.ac.it_college.std.s23019.messageboard.domain.model.Threads
import jp.ac.it_college.std.s23019.messageboard.domain.repository.ThreadsRepository
import org.springframework.stereotype.Service

@Service
class ThreadService(
    private val threadsRepository: ThreadsRepository
) {
    fun createThread(thread: Threads): Threads {
        return threadsRepository.createThread(thread)
    }
}