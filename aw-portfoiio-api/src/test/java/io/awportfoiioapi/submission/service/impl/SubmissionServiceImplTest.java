package io.awportfoiioapi.submission.service.impl;

import io.awportfoiioapi.RepositoryAndServiceTestSupport;
import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.submission.dto.request.SubmissionPostDraftRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubmissionServiceImplTest extends RepositoryAndServiceTestSupport {
    
    
    @DisplayName("작성폼 임시저장")
    @Test
    void test1() {
        
        SubmissionPostDraftRequest request = new SubmissionPostDraftRequest(null, 2L, 7L, "{sdsdsdsd}");
        
        ApiResponse apiResponse = submissionService.temporaryStorage(request);
        System.out.println("apiResponse = " + apiResponse);
    }
    
    @DisplayName("작성폼 임시저장(이미있는작성폼일경우)")
    @Test
    void test2() {
        SubmissionPostDraftRequest request = new SubmissionPostDraftRequest(2L, 2L, 7L, "{ㄷㅇㅇㅇㅇㅇㅇㅇㅇ}");
        
        ApiResponse apiResponse = submissionService.temporaryStorage(request);
        System.out.println("apiResponse = " + apiResponse);
        
    }
}