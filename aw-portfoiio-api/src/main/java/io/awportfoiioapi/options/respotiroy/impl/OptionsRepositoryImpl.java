package io.awportfoiioapi.options.respotiroy.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.awportfoiioapi.options.entity.Options;
import io.awportfoiioapi.options.respotiroy.query.OptionsQueryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    
    @Override
    public List<Options> findByQuestionId(Long id) {
        return queryFactory
                .selectFrom(options)
                .where(options.question.id.eq(id))
                .fetch();
    }
}
