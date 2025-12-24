package io.awportfoiioapi.userlist.service.impl;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.member.repository.MemberRepository;
import io.awportfoiioapi.userlist.dto.request.UserListPostRequest;
import io.awportfoiioapi.userlist.dto.response.UserListGetResponse;
import io.awportfoiioapi.userlist.service.UserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {
    
    private final MemberRepository memberRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserListGetResponse> getUserList(Pageable pageable) {
        return memberRepository.findByUserList(pageable);
    }
    
    @Override
    public ApiResponse createUserList(UserListPostRequest request) {
        return null;
    }
}
