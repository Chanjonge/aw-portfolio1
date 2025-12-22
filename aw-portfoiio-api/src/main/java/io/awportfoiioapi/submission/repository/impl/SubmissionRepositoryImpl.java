package io.awportfoiioapi.submission.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.awportfoiioapi.submission.repository.query.SubmissionQueryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubmissionRepositoryImpl implements SubmissionQueryRepository {
    private final JPAQueryFactory queryFactory;
}
