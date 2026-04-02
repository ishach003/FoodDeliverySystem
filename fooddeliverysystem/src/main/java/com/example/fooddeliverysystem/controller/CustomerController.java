package com.example.fooddeliverysystem.controller;

import com.example.fooddeliverysystem.config.constants.CustomerConstant;
import com.example.fooddeliverysystem.dto.ResponseDto;
import com.example.fooddeliverysystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")

public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<ResponseDto> getCustomerById(@PathVariable Integer customerId) {
        log.info("GET /api/customer/{}", customerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CustomerConstant.STATUS_200,
                        CustomerConstant.MESSAGE_200,
                        customerService.getCustomerById(customerId)));
    }

    @GetMapping("/{customerId}/addresses")
    public ResponseEntity<ResponseDto> getCustomerAddress(@PathVariable Integer customerId){
        log.info("GET /api/customer/{}/addresses", customerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CustomerConstant.STATUS_200,
                        CustomerConstant.MESSAGE_200,
                        customerService.getCustomerAddress(customerId)));
    }

}
