package io.awportfoiioapi.portfolio.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PortfoliosGetDetailResponse {
    
    private Long id;
    private Long portfolioId;
    private String description;
    private Boolean isRequired;
    private Integer maxLength;
    private Integer minLength;
    private String options;
    private Integer order;
    private String questionType;
    private Boolean requireMinLength;
    private Integer step;
    private String thumbnail;
    private String title;
    
    @QueryProjection
    public PortfoliosGetDetailResponse(Long id, Long portfolioId, String description, Boolean isRequired, Integer maxLength, Integer minLength, String options, Integer order, String questionType, Boolean requireMinLength, Integer step, String thumbnail, String title) {
        this.id = id;
        this.portfolioId = portfolioId;
        this.description = description;
        this.isRequired = isRequired;
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.options = options;
        this.order = order;
        this.questionType = questionType;
        this.requireMinLength = requireMinLength;
        this.step = step;
        this.thumbnail = thumbnail;
        this.title = title;
    }
}
