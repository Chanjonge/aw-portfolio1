package io.awportfoiioapi.portfolio.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.awportfoiioapi.portfolio.repository.query.PortfolioQueryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PortfolioRepositoryImpl implements PortfolioQueryRepository {

    private final JPAQueryFactory queryFactory;
}
