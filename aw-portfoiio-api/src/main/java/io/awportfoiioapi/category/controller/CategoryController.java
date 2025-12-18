package io.awportfoiioapi.category.controller;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.category.dto.request.CategoryPostRequest;
import io.awportfoiioapi.category.dto.request.CategoryPutRequest;
import io.awportfoiioapi.category.dto.response.CategoryGetResponse;
import io.awportfoiioapi.category.service.CategoryService;
import io.awportfoiioapi.member.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CategoryController {
    
    private final CategoryService categoryService;
    
    @GetMapping("/category")
    public PageResponse<CategoryGetResponse> getCategoryList(@PageableDefault(size = 10) Pageable pageable) {
        Page<CategoryGetResponse> categoryList = categoryService.getCategoryList(pageable);
        return PageResponse.from(categoryList);
    }
    
    @PostMapping("/category")
    public ApiResponse createCategory(@Validated @RequestBody CategoryPostRequest request) {
        return categoryService.createCategory(request);
    }
    
    @PutMapping("/category")
    public ApiResponse modifyCategory(@Validated @RequestBody CategoryPutRequest request) {
        return categoryService.modifyCategory(request);
    }
    
    @DeleteMapping("/category/{id}")
    public ApiResponse deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
