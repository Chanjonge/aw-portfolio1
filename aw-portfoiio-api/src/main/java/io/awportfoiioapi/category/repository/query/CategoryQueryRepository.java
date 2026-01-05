package io.awportfoiioapi.category.repository.query;

import io.awportfoiioapi.category.dto.response.CategoryCountResponse;
import io.awportfoiioapi.category.dto.response.CategoryGetResponse;
import io.awportfoiioapi.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryQueryRepository  {
    
    Page<CategoryGetResponse> getCategoryList(Pageable pageable);
    
    List<CategoryCountResponse> getCategoryCount();
    
    boolean existsByOrder(Integer order);
    
    boolean existsByOrder(Integer order, Long excludeCategoryId);
    
    boolean existsByPortfolio(Long id);
    
    List<Category> findAllOrders();
}
