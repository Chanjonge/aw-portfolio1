package io.awportfoiioapi.submission.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionPostDraftRequest {

    private Long submissionId;
    
    private Long memberId;
    
    private Long portfolioId;
    
    private String response;
    
}
