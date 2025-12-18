package io.awportfoiioapi.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryGetResponse {
    private Long id;
    private String name;
    private Integer order;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static class _count{
        private Integer portfolios;
    }
}
