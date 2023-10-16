package com.zerobase.zerobasewebsample.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class SampleController {

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId) throws IllegalAccessException {
        log.info("[LOG] Get some order :" + orderId);

        if ("500".equals(orderId)) {
            throw new IllegalAccessException("500 is not valid orderId");
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

    @Data
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;
    }
}
