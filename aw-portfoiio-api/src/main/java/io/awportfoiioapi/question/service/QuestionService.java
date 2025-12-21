package io.awportfoiioapi.question.service;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.question.dto.request.QuestionPostRequest;
import io.awportfoiioapi.question.dto.request.QuestionPutRequest;
import io.awportfoiioapi.question.dto.response.QuestionGerResponse;
import io.awportfoiioapi.question.dto.response.QuestionGetDetailResponse;

public interface QuestionService {

    QuestionGerResponse getQuestion(Long id);
    QuestionGetDetailResponse getQuestionDetail(Long id);
    ApiResponse createQuestion(QuestionPostRequest request);
    ApiResponse modifyQuestion(QuestionPutRequest request);
    ApiResponse deleteQuestion(Long ID);
}
