package jp.ac.it_college.std.s23019.messageboard.presentation.controller

import jp.ac.it_college.std.s23019.messageboard.application.service.MessageService
import jp.ac.it_college.std.s23019.messageboard.presentation.form.CreateMessageRequest
import jp.ac.it_college.std.s23019.messageboard.presentation.form.MessageResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("messages")
@CrossOrigin
class MessageController(
    private val messageService: MessageService
) {

    @PostMapping("/create")
    fun createMessage(@RequestBody createMessageRequest: CreateMessageRequest): ResponseEntity<MessageResponse> {
        val message = messageService.createMessage(createMessageRequest.toModel())
        return ResponseEntity.ok(MessageResponse.fromModel(message))
    }

    @GetMapping("/all")
    fun getAllMessages(): ResponseEntity<List<MessageResponse>> {
        val messages = messageService.getAllMessages().map { MessageResponse.fromModel(it) }
        return ResponseEntity.ok(messages)
    }
}
