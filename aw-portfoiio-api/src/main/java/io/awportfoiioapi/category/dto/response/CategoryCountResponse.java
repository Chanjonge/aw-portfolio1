package io.awportfoiioapi.category.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryCountResponse {
    
    private Long id;
    private Long cnt;
    
    @QueryProjection
    public CategoryCountResponse(Long id, Long cnt) {
        this.id = id;
        this.cnt = cnt;
    }
}
