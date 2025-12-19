package io.awportfoiioapi.advice.exception;

import io.awportfoiioapi.advice.response.ErrorMessageResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationException extends CustomException{
   

    private final ErrorMessageResponse errorResponse;
 
     public ValidationException(ErrorMessageResponse errorResponse) {
         super("VALIDATION_ERROR");
         this.errorResponse = errorResponse;
     }
 
     public ErrorMessageResponse getErrorResponse() {
         return errorResponse;
     }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
