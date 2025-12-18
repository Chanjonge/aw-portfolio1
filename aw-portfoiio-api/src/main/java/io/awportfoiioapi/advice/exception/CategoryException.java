package io.awportfoiioapi.advice.exception;

import org.springframework.http.HttpStatus;

public class CategoryException extends CustomException{
    
    public CategoryException(String message, String field) {
        super(message, field);
    }
    
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
