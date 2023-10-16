package com.zerobase.zerobasewebsample.dto;

import com.zerobase.zerobasewebsample.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;
}
