package io.awportfoiioapi.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPutRequest {
    
    @NotNull(message = "카테고리 ID가 존재하지 않습니다.")
    private Long id;
    @NotBlank(message = "카테고리 필수 입력입니다.")
    private String name;
    @NotNull(message = "카테고리 순서는 필수 입력입니다.")
    private Integer order;
    @NotBlank(message = "카테고리 슬러그는 필수 입력입니다.")
    private String slug;
}
