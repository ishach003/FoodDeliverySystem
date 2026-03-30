package com.example.fooddeliverysystem.service;

import com.example.fooddeliverysystem.dto.CustomerAddressDto;
import com.example.fooddeliverysystem.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getCustomerById(Integer customerId);

    List<CustomerAddressDto> getCustomerAddress(Integer customerId);
}
