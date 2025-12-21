package io.awportfoiioapi.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionPutRequest {
    private Integer step;
    private Integer order;
    private String title;
    private String description;
    private String type;
    private Integer minLength;
    private Integer maxLength;
    private Boolean requireMinLength;
    private Boolean isRequired;
    private ThumbnailRequest thumbnail;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ThumbnailRequest{
        private MultipartFile file;
        private Boolean remove;
    }
}
