package io.awportfoiioapi.portfolio.repository.query;

import io.awportfoiioapi.portfolio.entity.Portfolio;

public interface PortfolioQueryRepository {
    
    boolean existsByPortfolioOrder(Integer order);
}
