package com.itransition.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResult<T> {
    private boolean success;
    private String message;
    private T data;
    private List<ErrorData> errors;
    private HttpStatus httpStatus;
    private String type;

    public ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResult( String message ,boolean success,String type) {
        this.success = success;
        this.message = message;
        this.type = type;
    }

    public ApiResult(String message, HttpStatus httpStatus, boolean success) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.success = success;
    }


    public ApiResult(T data) {
        this.success = true;
        this.data = data;
    }

    public ApiResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
public ApiResult(boolean success, T data,String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public ApiResult(boolean success, List<ErrorData> errors) {
        this.success = success;
        this.errors = errors;
    }

    public static <E> ApiResult<E> successResponse(E data) {
        return new ApiResult<>(true, data);
    }

    public static <E> ApiResult<E> successResponse(E data,String message) {
        return new ApiResult<>(true, data,message);
    }

    public static <E> ApiResult<E> successResponse(String message) {
        return new ApiResult<>(true, message);
    }

    public static <E> ApiResult<E> badRequest(String message, HttpStatus status) {
        return new ApiResult<>(message, status, false);
    }

    public static <E> ApiResult<E> badRequest(String message,String type) {
        return new ApiResult<>(message, false,type);
    }


}
