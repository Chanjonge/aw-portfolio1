package io.awportfoiioapi.category.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CategoryGetResponse {
    private Long id;
    private String name;
    private Integer order;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private _Count count;
    
    @QueryProjection
    public CategoryGetResponse(Long id, String name, Integer order, String slug, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.slug = slug;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    

    
    public _Count getCount() {
        if (this.count == null) {
            this.count = new _Count();
        }
        return this.count;
    }
    
    @Data
    public static class _Count {
        private Long portfolios;
    }
}
