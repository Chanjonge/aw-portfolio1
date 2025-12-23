package io.awportfoiioapi.apiresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    
    private int code;
    private boolean success;
    private String message;
    private Long submissionId;
    
    public ApiResponse(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }
}
