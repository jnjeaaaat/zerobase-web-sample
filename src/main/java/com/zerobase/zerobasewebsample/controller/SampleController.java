package com.zerobase.zerobasewebsample.controller;

import com.zerobase.zerobasewebsample.dto.ErrorResponse;
import com.zerobase.zerobasewebsample.exception.ErrorCode;
import com.zerobase.zerobasewebsample.exception.WebSampleException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestController
@RequestMapping("/order")
public class SampleController {

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId) throws WebSampleException, SQLIntegrityConstraintViolationException {
        log.info("[LOG] Get some order :" + orderId);

        if ("500".equals(orderId)) {
            throw new WebSampleException(
                    ErrorCode.TOO_BIG_ID_ERROR,
                    "500 is too big orderId"
            );
        }

        if ("3".equals(orderId)) {
            throw new WebSampleException(
                    ErrorCode.TOO_SMALL_ID_ERROR,
                    "3 is too small orderId"
            );
        }

        if ("4".equals(orderId)) {
            throw new SQLIntegrityConstraintViolationException(
                    "Duplicated insertion was tried"
            );
        }

        return "orderId: " + orderId + ", " + "orderAmount: " + 9000;
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String orderId) {
        log.info("[LOG] Delete some order :" + orderId);
        return "Delete orderId: " + orderId;
    }

    @GetMapping("")
    public String getOrderWithRequestParam(
            @RequestParam(value = "orderId", required = false, defaultValue = "o123") String orderId,
            @RequestParam("orderAmount") String amount) {
        log.info("[LOG] Get some order :" + orderId + ", " + "amount : " + amount);
        return "orderId: " + orderId + ", " + "orderAmount: " + amount;
    }

    @PostMapping("")
    public String CreateOrderWithRequestBody(
            @RequestBody CreateOrderRequest createOrderRequest,
            @RequestHeader String userAccountId) {
        log.info("[LOG] Create Order :" + createOrderRequest +
                ", userAccountId : " + userAccountId);
        return "orderId: " + createOrderRequest.getOrderId() + ", "
                + "orderAmount: " + createOrderRequest.getOrderAmount();
    }

    @PostMapping("/2")
    public String createOrder() {
        log.info("[LOG] Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponse> handleIllegalAccessException(IllegalAccessException e) {
        log.error("IllegalAccessException is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR,
                        "IllegalAccessException is occurred."));
    }

    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handleWebSampleException(WebSampleException e) {
        log.error("WebSampleException is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.INSUFFICIENT_STORAGE)
                .body(new ErrorResponse(e.getErrorCode(),
                        "WebSampleException is occurred."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR,
                        "Exception is occurred."));
    }

    @Data
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;
    }
}
