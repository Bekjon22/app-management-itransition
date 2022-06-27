package com.itransition.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class RestException extends RuntimeException {

    private String message;

    private HttpStatus status;

    private boolean success;


    public RestException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public RestException(String message, HttpStatus status,boolean success) {
        this.message = message;
        this.status = status;
        this.success = success;
    }

    public static RestException notFound(String message) {
        return new RestException(message,HttpStatus.NOT_FOUND);
    }

    public static RestException badRequest(String message) {
        return new RestException(message, HttpStatus.BAD_REQUEST);
    }
    public static RestException badRequest(String message,boolean success) {
        return new RestException(message, HttpStatus.BAD_REQUEST);
    }

}
