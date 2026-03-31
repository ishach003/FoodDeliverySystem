package com.example.fooddeliverysystem.controller;

import com.example.fooddeliverysystem.config.constants.RestaurantConstant;
import com.example.fooddeliverysystem.dto.ResponseDto;
import com.example.fooddeliverysystem.service.DeliveryDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DeliveryDriverController {

    private final DeliveryDriverService driverService;

    //Get driver by ID
    @GetMapping("/{driverId}")
    public ResponseEntity<ResponseDto> getDriverById(@PathVariable Integer driverId) {
        log.info("GET /api/driver/{}", driverId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(RestaurantConstant.STATUS_200,
                        RestaurantConstant.MESSAGE_210,
                        driverService.getDriverById(driverId)));
    }
}
