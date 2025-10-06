package com.inventory.Exception;

import org.springframework.http.HttpStatus;

public class ProductServiceException extends RuntimeException {
	   

    private final HttpStatus httpStatus;     
    private final String message;            

    
    public ProductServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
