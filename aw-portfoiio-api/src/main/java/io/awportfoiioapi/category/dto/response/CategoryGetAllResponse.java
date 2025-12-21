package io.awportfoiioapi.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGetAllResponse {
    
    private Long id;
    private String name;
    private Integer order;
}
