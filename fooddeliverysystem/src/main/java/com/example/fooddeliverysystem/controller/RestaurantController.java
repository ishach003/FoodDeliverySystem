package com.example.fooddeliverysystem.controller;

import com.example.fooddeliverysystem.config.constants.RestaurantConstant;
import com.example.fooddeliverysystem.dto.ResponseDto;
import com.example.fooddeliverysystem.service.RestaurantService;
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
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // Get Restaurant By Id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getRestaurantById(@PathVariable Integer id) {

        log.info("GET /restaurants/{}", id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(
                        RestaurantConstant.STATUS_200,
                        RestaurantConstant.MESSAGE_210,
                        restaurantService.getRestaurantById(id)
                ));
    }
}