package com.example.fooddeliverysystem.controller;

import com.example.fooddeliverysystem.config.constants.RestaurantConstant;
import com.example.fooddeliverysystem.dto.ResponseDto;
import com.example.fooddeliverysystem.service.MenuItemService;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    // Get menu item by ID
    @GetMapping("/menuitems/{itemId}")
    public ResponseEntity<ResponseDto> getMenuItemById(@PathVariable Integer itemId) {
        log.info("GET /api/menuitems/{}", itemId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(
                        RestaurantConstant.STATUS_200,
                        RestaurantConstant.MESSAGE_210,
                        menuItemService.getMenuItemById(itemId)
                ));
    }

    //  Get menu items of a restaurant
    @GetMapping("/restaurant/{restaurantId}/menuitems")
    public ResponseEntity<ResponseDto> getMenuItemsByRestaurant(
            @PathVariable Integer restaurantId) {

        log.info("GET /api/restaurant/{}/menuitems", restaurantId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(
                        RestaurantConstant.STATUS_200,
                        RestaurantConstant.MESSAGE_210,
                        menuItemService.getMenuItemsByRestaurantId(restaurantId)
                ));
    }
}


