package io.awportfoiioapi.submission.repository.query;

import io.awportfoiioapi.submission.dto.response.SubmissionGetRequest;

public interface SubmissionQueryRepository {
    SubmissionGetRequest getSubmission(Long submissionId);
}
