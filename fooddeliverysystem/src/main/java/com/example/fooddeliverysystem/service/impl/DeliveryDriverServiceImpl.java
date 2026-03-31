package com.example.fooddeliverysystem.service.impl;

import com.example.fooddeliverysystem.config.CustomMapper;
import com.example.fooddeliverysystem.dto.DeliveryDriverDto;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.DeliveryDriverRepository;
import com.example.fooddeliverysystem.service.DeliveryDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryDriverServiceImpl implements DeliveryDriverService {

    private final DeliveryDriverRepository driverRepository;

    @Override
    public DeliveryDriverDto getDriverById(Integer driverId) {
        log.info("Fetching driver with id: {}", driverId);
        return driverRepository.findById(driverId)
                .map(CustomMapper::toDriverDto)
                .orElseThrow(() -> new ResourceNotFoundException("Driver", driverId));
    }
}
