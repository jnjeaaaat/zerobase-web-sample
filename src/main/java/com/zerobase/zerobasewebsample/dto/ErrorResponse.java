package com.zerobase.zerobasewebsample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String errorCode;
    private String message;
}
