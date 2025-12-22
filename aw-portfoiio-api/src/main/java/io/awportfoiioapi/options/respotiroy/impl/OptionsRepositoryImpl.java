package io.awportfoiioapi.options.respotiroy.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.awportfoiioapi.options.respotiroy.query.OptionsQueryRepository;
import lombok.RequiredArgsConstructor;

import static io.awportfoiioapi.options.entity.QOptions.*;

@RequiredArgsConstructor
public class OptionsRepositoryImpl implements OptionsQueryRepository {

    private final JPAQueryFactory queryFactory;
    
    @Override
    public Long countByQuestionId(Long questionId) {
        return queryFactory
                .select(options.count())
                .from(options)
                .where(options.question.id.eq(questionId))
                .fetchOne();
    }
}
