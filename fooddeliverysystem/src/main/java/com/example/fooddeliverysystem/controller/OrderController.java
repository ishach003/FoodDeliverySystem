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
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<ResponseDto> getOrdersByCustomerId(
            @PathVariable Integer customerId) {

        System.out.println("Hello print");

        log.info("GET /api/customers/{}/orders", customerId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(
                        OrderConstant.STATUS_200,
                        OrderConstant.MESSAGE_210,
                        orderService.getOrdersByCustomerId(customerId)
                ));
    }
}

