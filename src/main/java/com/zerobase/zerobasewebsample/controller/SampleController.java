package com.zerobase.zerobasewebsample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class SampleController {

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable String orderId) {
        log.info("[LOG] Get some order :" + orderId);
        return "orderId: " + orderId + ", " + "orderAmount: 1000";
    }

    @PostMapping("/2")
    public String createOrder() {
        log.info("[LOG] Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }
}
