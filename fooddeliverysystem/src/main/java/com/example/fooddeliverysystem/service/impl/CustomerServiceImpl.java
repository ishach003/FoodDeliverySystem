package com.example.fooddeliverysystem.service.impl;

import com.example.fooddeliverysystem.config.CustomMapper;
import com.example.fooddeliverysystem.dto.CustomerDto;
import com.example.fooddeliverysystem.entity.Customer;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.CustomerRepository;
import com.example.fooddeliverysystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
    private  final CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomerById(Integer customerId) {
        log.info("Fetching customer with id: {}" ,customerId);
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer",customerId));
        return CustomMapper.customerToCustomerDto(customer);
    }
}
