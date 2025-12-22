package io.awportfoiioapi.submission.repository;

import io.awportfoiioapi.submission.entity.Submission;
import io.awportfoiioapi.submission.repository.query.SubmissionQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission,Long>, SubmissionQueryRepository {
}
