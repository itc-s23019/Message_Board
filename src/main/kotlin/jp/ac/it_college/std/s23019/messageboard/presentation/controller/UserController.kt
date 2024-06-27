package jp.ac.it_college.std.s23019.messageboard.presentation.controller

import jp.ac.it_college.std.s23019.messageboard.application.service.UserService
import jp.ac.it_college.std.s23019.messageboard.infrastructure.database.entity.MessageBoardUserDetails
import jp.ac.it_college.std.s23019.messageboard.presentation.Form.GetUserInfoResponse
import jp.ac.it_college.std.s23019.messageboard.presentation.Form.PostUserRegisterRequest
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@CrossOrigin
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/register")
    fun register(@RequestBody request: PostUserRegisterRequest) {
        run {
            userService.register(request.viewName, request.email, request.password)
        }
    }

    @GetMapping("/info")
    fun getInfo(
        @AuthenticationPrincipal user: MessageBoardUserDetails
    ): GetUserInfoResponse {
        return userService.find(user.getId()).run {
            GetUserInfoResponse(id, viewName)
        }
    }
}