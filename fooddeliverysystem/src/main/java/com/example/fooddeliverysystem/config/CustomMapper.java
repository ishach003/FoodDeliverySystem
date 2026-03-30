package com.example.fooddeliverysystem.config;

import com.example.fooddeliverysystem.dto.*;
import com.example.fooddeliverysystem.entity.*;

public class CustomMapper {
    private CustomMapper(){}

    //Customer
    public static CustomerDto customerToCustomerDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setCustomerName(customer.getCustomerName());
        dto.setCustomerEmail(customer.getCustomerEmail());
        dto.setCustomerPhone(customer.getCustomerPhone());
        return dto;
    }
    public static Customer customerDtoToCustomer(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setCustomerName(dto.getCustomerName());
        customer.setCustomerEmail(dto.getCustomerEmail());
        customer.setCustomerPhone(dto.getCustomerPhone());
        return customer;
    }

    public static CustomerAddressDto addressToDTO(DeliveryAddress address) {
        CustomerAddressDto dto = new CustomerAddressDto();
        dto.setAddressId(address.getAddressId());
        dto.setAddressLine1(address.getAddressLine1());
        dto.setAddressLine2(address.getAddressLine2());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        return dto;
    }

    //Restaurant
    public static Restaurant toRestaurantEntity(RestaurantRequestDto dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(dto.getRestaurantName());
        restaurant.setRestaurantAddress(dto.getRestaurantAddress());
        restaurant.setRestaurantPhone(dto.getRestaurantPhone());
        return restaurant;
    }

    public static RestaurantResponseDto toRestaurantDto(Restaurant entity) {
        RestaurantResponseDto dto = new RestaurantResponseDto();
        dto.setRestaurantId(entity.getRestaurantId());
        dto.setRestaurantName(entity.getRestaurantName());
        dto.setRestaurantAddress(entity.getRestaurantAddress());
        dto.setRestaurantPhone(entity.getRestaurantPhone());
        return dto;
    }

    //MenuItem
    public static MenuItem toMenuItemEntity(MenuItemRequestDto dto) {
        MenuItem menuItem = new MenuItem();
        menuItem.setItemName(dto.getItemName());
        menuItem.setItemDescription(dto.getItemDescription());
        menuItem.setItemPrice(dto.getItemPrice());
        return menuItem;

    }
    public static MenuItemResponseDto toMenuItemDto(MenuItem entity) {
        MenuItemResponseDto dto = new MenuItemResponseDto();
        dto.setItemId(entity.getItemId());
        dto.setItemName(entity.getItemName());
        dto.setItemDescription(entity.getItemDescription());
        dto.setItemPrice(entity.getItemPrice());
        if (entity.getRestaurant() != null) {
            dto.setRestaurantId(entity.getRestaurant().getRestaurantId());
        }
        return dto;
    }

    //Coupon
    public static CouponDto toCouponDto(Coupon entity) {
        CouponDto dto = new CouponDto();
        dto.setCouponId(entity.getCouponId());
        dto.setCouponCode(entity.getCouponCode());
        dto.setDiscountAmount(entity.getDiscountAmount());
        dto.setExpiryDate(entity.getExpiryDate());
        return dto;
    }

    //Rating
    public static RatingDto toRatingDto(Rating entity) {
        RatingDto dto = new RatingDto();
        dto.setRatingId(entity.getRatingId());
        dto.setRating(entity.getRating());
        dto.setReview(entity.getReview());
        if (entity.getRestaurant() != null) {
            dto.setRestaurantId(entity.getRestaurant().getRestaurantId());
        }
        if (entity.getOrder() != null) {
            dto.setOrderId(entity.getOrder().getOrderId());
        }
        return dto;
    }

    //DeliveryDriver
    public static DeliveryDriverDto toDriverDto(DeliveryDriver entity) {
        DeliveryDriverDto dto = new DeliveryDriverDto();
        dto.setDriverId(entity.getDriverId());
        dto.setDriverName(entity.getDriverName());
        dto.setDriverPhone(entity.getDriverPhone());
        dto.setDriverVehicle(entity.getDriverVehicle());
        return dto;
    }
}
