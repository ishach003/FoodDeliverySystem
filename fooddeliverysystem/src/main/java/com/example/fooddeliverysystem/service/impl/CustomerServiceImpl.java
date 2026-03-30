package com.example.fooddeliverysystem.service.impl;

import com.example.fooddeliverysystem.config.CustomMapper;
import com.example.fooddeliverysystem.dto.CustomerAddressDto;
import com.example.fooddeliverysystem.dto.CustomerDto;
import com.example.fooddeliverysystem.entity.Customer;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.CustomerRepository;
import com.example.fooddeliverysystem.repository.DeliveryAddressRepository;
import com.example.fooddeliverysystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
    private  final CustomerRepository customerRepository;
    private  final DeliveryAddressRepository addressRepository;

    @Override
    public CustomerDto getCustomerById(Integer customerId) {
        log.info("Fetching customer with id: {}" ,customerId);
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer",customerId));
        return CustomMapper.customerToCustomerDto(customer);
    }

    @Override
    public List<CustomerAddressDto> getCustomerAddress(Integer customerId) {
       log.info("Fetching customer addresses by customer Id :{}",customerId);
       customerRepository.findById(customerId)
               .orElseThrow(()->new ResourceNotFoundException("Customer",customerId));
       return addressRepository.findByCustomer_CustomerId(customerId)
               .stream()
               .map(CustomMapper::addressToDTO)
               .toList();
    }
}
