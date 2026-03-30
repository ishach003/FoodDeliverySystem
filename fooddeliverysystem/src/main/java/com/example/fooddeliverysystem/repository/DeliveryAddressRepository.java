package com.example.fooddeliverysystem.repository;

import com.example.fooddeliverysystem.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Integer> {
    List<DeliveryAddress> findByCustomer_CustomerId(Integer customerId);
}
