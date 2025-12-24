package io.awportfoiioapi.userlist.controller;


import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.member.page.PageResponse;
import io.awportfoiioapi.userlist.dto.request.UserListPostRequest;
import io.awportfoiioapi.userlist.dto.response.UserListGetResponse;
import io.awportfoiioapi.userlist.service.UserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserListController {

    private final UserListService userListService;
    
    
    @GetMapping("/user-list")
    public PageResponse<UserListGetResponse> getUserList(@PageableDefault(size = 10) Pageable pageable) {
        Page<UserListGetResponse> userList = userListService.getUserList(pageable);
        return PageResponse.from(userList);
    }
    
    @PostMapping("/user-list")
    public ApiResponse createUserList(@Validated @RequestBody UserListPostRequest request) {
        return userListService.createUserList(request);
    }
}
