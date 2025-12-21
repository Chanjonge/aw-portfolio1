package io.awportfoiioapi.category.service.impl;

import io.awportfoiioapi.advice.exception.CategoryAndPortfolioException;
import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.category.dto.request.CategoryPostRequest;
import io.awportfoiioapi.category.dto.request.CategoryPutRequest;
import io.awportfoiioapi.category.dto.response.CategoryCountResponse;
import io.awportfoiioapi.category.dto.response.CategoryGetAllResponse;
import io.awportfoiioapi.category.dto.response.CategoryGetResponse;
import io.awportfoiioapi.category.entity.Category;
import io.awportfoiioapi.category.repository.CategoryRepository;
import io.awportfoiioapi.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    @Override
    public Page<CategoryGetResponse> getCategoryList(Pageable pageable) {
        Page<CategoryGetResponse> categoryList = categoryRepository.getCategoryList(pageable);
        List<CategoryCountResponse> counts = categoryRepository.getCategoryCount();
        Map<Long, Long> countMap =
                counts.stream()
                        .collect(Collectors.toMap(
                                CategoryCountResponse::getId,
                                CategoryCountResponse::getCnt
                        ));
        categoryList.forEach(category ->
                category.getCount()
                        .setPortfolios(countMap.getOrDefault(category.getId(), 0L))
        );
        return categoryList;
    }
    
    @Override
    public List<CategoryGetAllResponse> getAllResponse() {
        List<Category> categoryAll = categoryRepository.findAll();
        return categoryAll
                .stream()
                .map(item -> new CategoryGetAllResponse(item.getId(), item.getCategoryName(), item.getCategoryOrders()))
                .collect(Collectors.toList());
    }
    
    @Override
    public ApiResponse createCategory(CategoryPostRequest request) {
        Integer order = request.getOrder();
        boolean result = categoryRepository.existsByOrder(order);
        if (result) {
            throw new CategoryAndPortfolioException("이미 존재 하는 카테고리 순서입니다.", "order");
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
        Category category = categoryRepository.findById(request.getId())
                .orElseThrow(() -> new CategoryAndPortfolioException(
                        "존재하지 않는 카테고리입니다.",
                        "id"
                ));
        
        Integer newOrder = request.getOrder();
        Integer currentOrder = category.getCategoryOrders();
        
        // order가 변경된 경우에만 중복 체크
        if (!currentOrder.equals(newOrder)) {
            
            boolean exists = categoryRepository.existsByOrder(
                    newOrder,
                    category.getId() // 자기 자신 제외
            );
            
            if (exists) {
                throw new CategoryAndPortfolioException(
                        "이미 존재하는 카테고리 순서입니다.",
                        "order"
                );
            }
        }
        category.modify(request);
        
        return new ApiResponse(200, true, "카테고리가 수정되었습니다.");
    }
    
    @Override
    public ApiResponse deleteCategory(Long id) {
        boolean result = categoryRepository.existsByPortfolio(id);
        if (result) {
            throw new CategoryAndPortfolioException("해당 카테고리에 등록된 포트폴리오가 있어 삭제할 수 없습니다.", "portfolio");
        }
        categoryRepository.deleteById(id);
        return new ApiResponse(200, true, "카테고리가 삭제되었습니다.");
    }
}
