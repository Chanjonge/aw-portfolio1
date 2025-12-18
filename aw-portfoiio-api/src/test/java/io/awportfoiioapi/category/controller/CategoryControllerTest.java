package io.awportfoiioapi.category.controller;

import io.awportfoiioapi.ControllerTestSupport;
import io.awportfoiioapi.category.dto.request.CategoryPostRequest;
import io.awportfoiioapi.category.dto.request.CategoryPutRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;


class CategoryControllerTest extends ControllerTestSupport {

    
    @DisplayName("컨트롤러 조회 테스트")
    @Test
    void test1() throws Exception {
        mockMvc.perform(get("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print());
    }
    
    @DisplayName("컨트롤러 생성 테스트")
    @Test
    void test2() throws Exception {
        CategoryPostRequest request = new CategoryPostRequest("테스트형111", 3, "https://test");
        String json = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(print());
    }
    
    @DisplayName("컨트롤러 수정 테스트")
    @Test
    void test3() throws Exception {
        CategoryPutRequest request = new CategoryPutRequest(4L,"테스트형xxx", 3, "https://test");
        String json = objectMapper.writeValueAsString(request);
        mockMvc.perform(put("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(print());
    }
    
    @DisplayName("컨트롤러 삭제 테스트")
    @Test
    void test4() throws Exception {
        mockMvc.perform(delete("/api/category/{id}",4L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }
}