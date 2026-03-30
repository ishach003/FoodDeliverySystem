package com.example.fooddeliverysystem.service;

import com.example.fooddeliverysystem.dto.CustomerDto;

public interface CustomerService {
    CustomerDto getCustomerById(Integer customerId);
}
