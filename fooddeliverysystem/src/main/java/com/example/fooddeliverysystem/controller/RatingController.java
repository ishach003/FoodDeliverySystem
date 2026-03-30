package com.example.fooddeliverysystem.controller;

import com.example.fooddeliverysystem.config.constants.RestaurantConstant;
import com.example.fooddeliverysystem.dto.ResponseDto;
import com.example.fooddeliverysystem.service.RatingService;
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
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    // API #11 — Get rating by ID
    @GetMapping("/{ratingId}")
    public ResponseEntity<ResponseDto> getRatingById(@PathVariable Integer ratingId) {
        log.info("GET /api/v1/rating/{}", ratingId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(RestaurantConstant.STATUS_200,
                        RestaurantConstant.MESSAGE_210,
                        ratingService.getRatingById(ratingId)));

    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<ResponseDto> getRatingsByRestaurant(@PathVariable Integer restaurantId) {
        log.info("GET /api/v1/rating/restaurant/{}", restaurantId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(RestaurantConstant.STATUS_200,
                        RestaurantConstant.MESSAGE_210,
                        ratingService.getRatingsByRestaurant(restaurantId)));
    }

}
