package io.awportfoiioapi.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionPostRequest {
    private Integer step;
    private Integer order;
    private String title;
    private String description;
    private String type;
    private MultipartFile thumbnail;
    private Integer minLength;
    private Integer maxLength;
    private Boolean requireMinLength;
    private Boolean isRequired;
}
