package io.awportfoiioapi.question.respotiroy.impl;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.awportfoiioapi.options.entity.QOptions;
import io.awportfoiioapi.question.dto.response.QuestionGetResponse;
import io.awportfoiioapi.question.entity.Question;
import io.awportfoiioapi.question.respotiroy.query.QuestionQueryRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.awportfoiioapi.options.entity.QOptions.*;
import static io.awportfoiioapi.question.entity.QQuestion.question;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionQueryRepository {
    
    private final JPAQueryFactory queryFactory;
    
    @Override
    public Boolean existsStep(Long portfolioId, Integer step) {
        return queryFactory
                .selectOne()
                .from(question)
                .where(question.portfolio.id.eq(portfolioId), question.step.eq(step))
                .fetchFirst() != null;
    }
    
    @Override
    public Boolean existsOrders(Long portfolioId, Integer step, Integer order) {
        return queryFactory
                .selectOne()
                .from(options)
                .join(options.question, question)
                .where(
                        question.portfolio.id.eq(portfolioId),
                        question.step.eq(step),
                        options.orders.eq(order)
                )
                .fetchFirst() != null;
    }
    
    @Override
    public Question findByPortfolioStep(Long portfolioId, Integer step) {
        return queryFactory
                .selectFrom(question)
                .where(question.portfolio.id.eq(portfolioId), question.step.eq(step))
                .fetchFirst();
    }
    
    @Override
    public List<QuestionGetResponse> findByQuestions(Long portfolioId) {
      
        Map<Long, QuestionGetResponse> result =
              queryFactory
                  .from(options)
                  .join(options.question, question)
                  .where(question.portfolio.id.eq(portfolioId))
                  .transform(
                      GroupBy.groupBy(options.id).as(
                          Projections.constructor(
                              QuestionGetResponse.class,
                              options.id,
                              question.id,
                              question.step,
                              options.orders,
                              options.title,
                              options.description,
                              options.type.stringValue().toLowerCase(),
                              options.thumbnail,
                              options.maxLength,
                              options.minLength,
                              options.minLengthIsActive,
                              options.optionsIsActive
                          )
                      )
                  );
        
          return new ArrayList<>(result.values());
    }
}
