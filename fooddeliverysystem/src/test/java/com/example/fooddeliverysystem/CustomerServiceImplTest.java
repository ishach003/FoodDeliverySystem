package com.example.fooddeliverysystem;

import com.example.fooddeliverysystem.dto.CustomerDto;
import com.example.fooddeliverysystem.entity.Customer;
import com.example.fooddeliverysystem.entity.DeliveryAddress;
import com.example.fooddeliverysystem.repository.CustomerRepository;
import com.example.fooddeliverysystem.repository.DeliveryAddressRepository;
import com.example.fooddeliverysystem.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock private CustomerRepository customerRepository;
    @Mock private DeliveryAddressRepository addressRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;
    private Customer customer;
    private DeliveryAddress address;

    @BeforeEach
    void setUp(){
        customer =new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("John Smith");
        customer.setCustomerEmail("john@example.com");
        customer.setCustomerPhone("+1234567890");

        address=new DeliveryAddress();
        address.setAddressId(1);
        address.setAddressLine1("123 Elm St");
        address.setAddressLine2("");
        address.setCity("New York");
        address.setState("NY");
        address.setPostalCode("10001");
        address.setCustomer(customer);
    }

    @Nested
    @DisplayName("API-getCustomerById()")
    class GetCustomerById{
        @Test
        @DisplayName("PositiveTest")
        void positiveTestGetCustomerById(){
            when(customerRepository.findById(1))
                    .thenReturn(Optional.of(customer));

            CustomerDto result=customerService.getCustomerById(1);

            assertThat(result).isNotNull();
            assertThat(result.getCustomerName()).isEqualTo("John Smith");
            assertThat(result.getCustomerEmail()).isEqualTo("john@example.com");
            assertThat(result.getCustomerPhone()).isEqualTo("+1234567890");

            verify(customerRepository,times(1)).findById(1);
        }
    }
}
