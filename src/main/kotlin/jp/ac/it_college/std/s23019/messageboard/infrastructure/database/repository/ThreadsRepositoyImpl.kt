package jp.ac.it_college.std.s23019.messageboard.infrastructure.database.repository

import jp.ac.it_college.std.s23019.messageboard.domain.model.Threads
import jp.ac.it_college.std.s23019.messageboard.domain.repository.ThreadsRepository
import jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao.ThreadsEntity
import jp.ac.it_college.std.s23019.messageboard.infrastructure.database.dao.UsersEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository


@Repository
class ThreadsRepositoyImpl : ThreadsRepository{
    override fun createThread(thread: Threads): Threads {
        return transaction {
            val newthread = ThreadsEntity.new {
                title = thread.title
                userId = UsersEntity[thread.userId]
                createdAt = thread.createdAT
                updatedAt = thread.updatedAt
                deleted = thread.deleted
            }
            newthread.toThread()
        }
    }

    override fun getThreadById(id: Long): Threads? {
        return transaction {
            val threads = ThreadsEntity.findById(id)
            threads?.toThread()
        }
    }

    override fun updateThread(thread: Threads): Threads {
        return transaction {
            val threadsEntity = ThreadsEntity.findById(thread.id)
                ?: throw IllegalArgumentException("Threads not found with id: ${thread.id}")

            threadsEntity.apply {
                title = thread.title
                userId = UsersEntity[thread.userId]
                createdAt = thread.createdAT
                updatedAt = thread.updatedAt
                deleted = thread.deleted
            }
            threadsEntity.toThread()
        }
    }

    override fun deleteThread(id: Long) {
        transaction {
            ThreadsEntity.findById(id)?.delete()
        }
    }

}