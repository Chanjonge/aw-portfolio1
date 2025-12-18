package io.awportfoiioapi.category.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.awportfoiioapi.category.repository.query.CategoryQueryRepository;
import io.awportfoiioapi.portfolio.entity.QPortfolio;
import lombok.RequiredArgsConstructor;

import static io.awportfoiioapi.category.entity.QCategory.category;
import static io.awportfoiioapi.portfolio.entity.QPortfolio.*;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryQueryRepository {

    private final JPAQueryFactory queryFactory;
    
    @Override
    public boolean existsByOrder(Integer order) {
        return queryFactory
                .selectFrom(category)
                .where(category.categoryOrders.eq(order))
                .fetchFirst() != null;
    }
    
    @Override
    public boolean existsByPortfolio(Long id) {
        return queryFactory
                .select(portfolio)
                .from(portfolio)
                .join(portfolio.category , category)
                .where(category.id.eq(id))
                .fetchFirst() != null;
    }
}
