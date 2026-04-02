package com.example.fooddeliverysystem.controller;

import com.example.fooddeliverysystem.config.constants.RestaurantConstant;
import com.example.fooddeliverysystem.dto.ResponseDto;
import com.example.fooddeliverysystem.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@Slf4j
@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    // Get rating by ID
    @GetMapping("/{ratingId}")
    public ResponseEntity<ResponseDto> getRatingById(@PathVariable Integer ratingId) {
        log.info("GET /api/rating/{}", ratingId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(RestaurantConstant.STATUS_200,
                        RestaurantConstant.MESSAGE_210,
                        ratingService.getRatingById(ratingId)));

    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<ResponseDto> getRatingsByRestaurant(@PathVariable Integer restaurantId) {
        log.info("GET /api/rating/restaurant/{}", restaurantId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(RestaurantConstant.STATUS_200,
                        RestaurantConstant.MESSAGE_210,
                        ratingService.getRatingsByRestaurant(restaurantId)));
    }
}







