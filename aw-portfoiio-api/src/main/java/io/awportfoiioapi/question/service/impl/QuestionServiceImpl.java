package io.awportfoiioapi.question.service.impl;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.question.dto.request.QuestionPostRequest;
import io.awportfoiioapi.question.dto.request.QuestionPutRequest;
import io.awportfoiioapi.question.dto.response.QuestionGerResponse;
import io.awportfoiioapi.question.dto.response.QuestionGetDetailResponse;
import io.awportfoiioapi.question.respotiroy.QuestionRepository;
import io.awportfoiioapi.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    
    @Override
    public QuestionGerResponse getQuestion(Long id) {
        return null;
    }
    
    @Override
    public QuestionGetDetailResponse getQuestionDetail(Long id) {
        return null;
    }
    
    @Override
    public ApiResponse createQuestion(QuestionPostRequest request) {
        return null;
    }
    
    @Override
    public ApiResponse modifyQuestion(QuestionPutRequest request) {
        return null;
    }
    
    @Override
    public ApiResponse deleteQuestion(Long ID) {
        return null;
    }
}
