package io.awportfoiioapi.portfolio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortfolioPutRequest {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private String domain;
    private Integer order;
    private String slug;
    private ThumbnailRequest thumbnail;
    private Boolean isActive;
    
    @Data
    public static class ThumbnailRequest{
        private MultipartFile file;
        private Boolean remove;
    }
}
