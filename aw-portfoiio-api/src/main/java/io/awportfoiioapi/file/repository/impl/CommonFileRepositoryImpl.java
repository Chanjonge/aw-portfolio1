package io.awportfoiioapi.file.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.awportfoiioapi.file.repository.query.CommonFileQueryRepository;
import io.awportfoiioapi.portfolio.entity.QPortfolio;
import lombok.RequiredArgsConstructor;

import static io.awportfoiioapi.portfolio.entity.QPortfolio.*;

@RequiredArgsConstructor
public class CommonFileRepositoryImpl implements CommonFileQueryRepository {

    private final JPAQueryFactory queryFactory;
    
    @Override
    public boolean existsByOrder(Integer order) {
        return queryFactory
                .selectFrom(portfolio)
                .where(portfolio.orders.eq(order))
                .fetchFirst() != null;
    }
}
