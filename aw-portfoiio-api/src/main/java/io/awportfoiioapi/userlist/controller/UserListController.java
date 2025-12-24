package io.awportfoiioapi.userlist.controller;


import io.awportfoiioapi.member.page.PageResponse;
import io.awportfoiioapi.userlist.dto.response.UserListGetResponse;
import io.awportfoiioapi.userlist.service.UserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
