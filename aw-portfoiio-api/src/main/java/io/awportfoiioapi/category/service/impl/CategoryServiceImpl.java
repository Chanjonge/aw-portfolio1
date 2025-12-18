package io.awportfoiioapi.category.service.impl;

import io.awportfoiioapi.advice.exception.CategoryException;
import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.category.dto.request.CategoryPostRequest;
import io.awportfoiioapi.category.dto.request.CategoryPutRequest;
import io.awportfoiioapi.category.dto.response.CategoryGetResponse;
import io.awportfoiioapi.category.entity.Category;
import io.awportfoiioapi.category.repository.CategoryRepository;
import io.awportfoiioapi.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    @Override
    public List<CategoryGetResponse> getCategoryList(Pageable pageable) {
        return List.of();
    }
    
    @Override
    public ApiResponse createCategory(CategoryPostRequest request) {
        Integer order = request.getOrder();
        boolean result = categoryRepository.existsByOrder(order);
        if (result) {
            throw new CategoryException("이미 존재 하는 카테고리 순서입니다.", "order");
        }
        Category category = Category.builder()
                .categoryName(request.getName())
                .categoryOrders(request.getOrder())
                .categorySlug(request.getSlug())
                .build();
        categoryRepository.save(category);
        return new ApiResponse(200, true, "카테고리가 생성되었습니다.");
    }
    
    @Override
    public ApiResponse modifyCategory(CategoryPutRequest request) {
        Integer order = request.getOrder();
        boolean result = categoryRepository.existsByOrder(order);
        if (result) {
            throw new CategoryException("이미 존재 하는 카테고리 순서입니다.", "order");
        }
        Category category = categoryRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("존재하지않는 카테고리입니다."));
        category.modify(request);
        return new ApiResponse(200, true, "카테고리가 수정되었습니다.");
    }
    
    @Override
    public ApiResponse deleteCategory(Long id) {
        boolean result = categoryRepository.existsByPortfolio(id);
        if (result) {
            throw new CategoryException("해당 카테고리에 등록된 포트폴리오가 있어 삭제할 수 없습니다.","portfolio");
        }
        categoryRepository.deleteById(id);
        return new ApiResponse(200,true,"카테고리가 삭제되었습니다.");
    }
}
