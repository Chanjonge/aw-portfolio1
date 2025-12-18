package io.awportfoiioapi.category.repository.query;

public interface CategoryQueryRepository  {
    
    boolean existsByOrder(Integer order);
    
    boolean existsByPortfolio(Long id);
}
