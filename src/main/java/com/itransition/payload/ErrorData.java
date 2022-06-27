package com.itransition.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorData {
    private String errorMsg;
    private Integer errorCode;
    private String fieldName;

    public ErrorData(String errorMsg, Integer errorCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }
}
