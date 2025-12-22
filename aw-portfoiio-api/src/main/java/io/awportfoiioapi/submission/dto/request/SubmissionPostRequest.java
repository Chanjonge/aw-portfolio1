package io.awportfoiioapi.submission.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionPostRequest {
    
    private Long portfolioId;
    
    private String companyName;
    
    private String password;
    
    private String response;
    
    private Boolean isDraft;
}
