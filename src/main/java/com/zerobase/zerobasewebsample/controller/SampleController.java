package com.zerobase.zerobasewebsample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class SampleController {

    @GetMapping("")
    public String getOrderWithRequestParam(
            @RequestParam("orderId") String orderId,
            @RequestParam("orderAmount") String amount) {
        log.info("[LOG] Get some order :" + orderId + ", " + "amount : " + amount);
        return "orderId: " + orderId + ", " + "orderAmount: " + amount;
    }

    @PostMapping("/2")
    public String createOrder() {
        log.info("[LOG] Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }
}
