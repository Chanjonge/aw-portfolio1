package io.awportfoiioapi.submission.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SubmissionGetRequest {
    
    private Long submissionId;
    private Long portfolioId;
    private String companyName;
    private Boolean isDraft;
    private String submissionJson;
    private LocalDateTime completedDate;
    @QueryProjection
    public SubmissionGetRequest(Long submissionId, Long portfolioId, String companyName, Boolean isDraft, String submissionJson, LocalDateTime completedDate) {
        this.submissionId = submissionId;
        this.portfolioId = portfolioId;
        this.companyName = companyName;
        this.isDraft = isDraft;
        this.submissionJson = submissionJson;
        this.completedDate = completedDate;
    }
}
