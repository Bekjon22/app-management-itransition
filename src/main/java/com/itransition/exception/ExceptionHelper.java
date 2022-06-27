package com.itransition.exception;
import com.itransition.payload.ApiResult;
import com.itransition.payload.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestControllerAdvice
public class ExceptionHelper {


    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<?> exceptionHandler(RestException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(new ApiResult<>(false, Arrays.asList(new ErrorData(e.getMessage(), e.getStatus().value()))));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> exceptionHandler(MethodArgumentNotValidException e) {
        List<ErrorData> errorDataList = new ArrayList<>();

        List<ObjectError> allErrors = e.getAllErrors();
        for (ObjectError error : allErrors) {
            FieldError fieldError = (FieldError) error;
            ErrorData errorData = new ErrorData(error.getDefaultMessage(), HttpStatus.BAD_REQUEST.value(),fieldError.getField());
            errorDataList.add(errorData);
        }
        ApiResult<Object> apiResult = new ApiResult<>(false, errorDataList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> exceptionHandler(Exception e){
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResult<>(false, "INTERNAL_SERVER_ERROR"));
    }


}
