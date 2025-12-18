package io.awportfoiioapi.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortfolioResponse {
    private Long id;
    private String title;
    private String description;
    private String domain;
    private Integer order;
    private String slug;
    private String thumbnail;
    private Boolean isActive;
    private Long question;
    private Long submit;
}
