package io.awportfoiioapi.question.service;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.question.dto.request.QuestionPostRequest;
import io.awportfoiioapi.question.dto.request.QuestionPutRequest;
import io.awportfoiioapi.question.dto.response.QuestionGetResponse;
import io.awportfoiioapi.question.dto.response.QuestionGetDetailResponse;

import java.util.List;

public interface QuestionService {

    List<QuestionGetResponse> getQuestion(Long portfolioId);
    ApiResponse createQuestion(QuestionPostRequest request);
    ApiResponse modifyQuestion(QuestionPutRequest request);
    ApiResponse deleteQuestion(Long ID);
}
