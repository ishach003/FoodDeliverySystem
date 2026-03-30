package com.example.fooddeliverysystem.controller;

import com.example.fooddeliverysystem.config.constants.OrderConstant;
import com.example.fooddeliverysystem.dto.ResponseDto;
import com.example.fooddeliverysystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("customers/{customerId}/orders")
    public ResponseEntity<ResponseDto> getOrdersByCustomerId(
            @PathVariable Integer customerId) {

        log.info("GET /api/customers/{}/orders", customerId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(
                        OrderConstant.STATUS_200,
                        OrderConstant.MESSAGE_210,
                        orderService.getOrdersByCustomerId(customerId)
                ));
    }
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ResponseDto> getOrderDetailsById(
            @PathVariable Integer orderId) {
        log.info("/api/orders/{}", orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(OrderConstant.STATUS_200,
                        OrderConstant.MESSAGE_210,
                        orderService.getOrderDetailsById(orderId)));
    }
}

