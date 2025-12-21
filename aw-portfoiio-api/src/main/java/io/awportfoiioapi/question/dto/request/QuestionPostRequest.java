package io.awportfoiioapi.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionPostRequest {
    private Long portfolioId;
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
    private List<Notifications> notifications;
    
    public List<Notifications> getNotifications() {
        if (this.notifications == null) {
            this.notifications = new ArrayList<>();
        }
        return this.notifications;
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Notifications {
        private String value;
    }
}
