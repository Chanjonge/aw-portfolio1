package io.awportfoiioapi.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortfolioGetDetailResponse {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private String domain;
    private Integer order;
    private String slug;
    private String thumbnail;
    private Boolean isActive;
}
