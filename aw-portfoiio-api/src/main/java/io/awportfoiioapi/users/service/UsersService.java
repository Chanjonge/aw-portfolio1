package io.awportfoiioapi.users.service;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.member.page.PageResponse;
import io.awportfoiioapi.users.dto.request.UsersPasswordPostRequest;
import io.awportfoiioapi.users.dto.response.UsersGetResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsersService {
    
    
    PageResponse<UsersGetResponse> getUsers(Pageable pageable);
    
    ApiResponse modifyPassword(UsersPasswordPostRequest  request);
    
    ApiResponse deleteUser(Long id);
    
}
