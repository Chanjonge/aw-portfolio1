package io.awportfoiioapi.category.service;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.category.dto.request.CategoryPostRequest;
import io.awportfoiioapi.category.dto.request.CategoryPutRequest;
import io.awportfoiioapi.category.dto.response.CategoryGetResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    
    Page<CategoryGetResponse> getCategoryList(Pageable pageable);
    ApiResponse createCategory(CategoryPostRequest request);
    ApiResponse modifyCategory(CategoryPutRequest request);
    ApiResponse deleteCategory(Long id);
    
}
