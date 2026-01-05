package io.awportfoiioapi.category.service.impl;

import io.awportfoiioapi.RepositoryAndServiceTestSupport;
import io.awportfoiioapi.advice.exception.CustomException;
import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.category.dto.request.CategoryPostRequest;
import io.awportfoiioapi.category.dto.request.CategoryPutRequest;
import io.awportfoiioapi.category.dto.response.CategoryGetAllResponse;
import io.awportfoiioapi.category.dto.response.CategoryGetResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CategoryServiceImplTest extends RepositoryAndServiceTestSupport {
    
    @DisplayName("카테고리 생성")
    @Test
    void test1() {
        
        CategoryPostRequest request = new CategoryPostRequest("테스트형", 1, "https://test");
        
        ApiResponse category = categoryService.createCategory(request);
        
        System.out.println("category = " + category);
        
    }
    
    @DisplayName("카테고리 생성 검증(순서)")
    @Test
    void test2() {
        CategoryPostRequest request = new CategoryPostRequest("독채형", 1, "https://test");
        assertThatThrownBy(() ->
                categoryService.createCategory(request)
        )
                .isInstanceOf(CustomException.class)
                .hasMessage("이미 존재 하는 카테고리 순서입니다.");
    }
    
    @DisplayName("카테고리 수정")
    @Test
    void test3() {
        CategoryPutRequest request = new CategoryPutRequest(1L, "독채형", 2, "https://dddddd");
        ApiResponse category = categoryService.modifyCategory(request);
        System.out.println("category = " + category);
    }
    
    @DisplayName("카테고리 수정 검증(순서)")
    @Test
    void test4() {
        CategoryPutRequest request = new CategoryPutRequest(1L, "독채형", 2, "https://sdsdsdsd");
        assertThatThrownBy(() ->
                categoryService.modifyCategory(request)
        )
                .isInstanceOf(CustomException.class)
                .hasMessage("이미 존재 하는 카테고리 순서입니다.");
    }
    
    @DisplayName("카테고리 삭제")
    @Test
    void test5() {
        ApiResponse apiResponse = categoryService.deleteCategory(2L);
        System.out.println("apiResponse = " + apiResponse);
    }
    
    @DisplayName("카테고리 삭제 검증(포트폴리오)")
    @Test
    void test6() {
        assertThatThrownBy(() ->
                categoryService.deleteCategory(1L)
        )
                .isInstanceOf(CustomException.class)
                .hasMessage("해당 카테고리에 등록된 포트폴리오가 있어 삭제할 수 없습니다.");
    }
    
    @DisplayName("카테고리 조회(카운트포함)")
    @Test
    void test7() {
        
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<CategoryGetResponse> categoryList = categoryService.getCategoryList(pageRequest);
        
        System.out.println("categoryList = " + categoryList);
    }
    
    @DisplayName("카테고리 전체조회(포폴관리나 등등 쓸것)")
    @Test
    void test8() {
        List<CategoryGetAllResponse> allResponse = categoryService.getAllResponse();
        System.out.println("allResponse = " + allResponse);
    }
}