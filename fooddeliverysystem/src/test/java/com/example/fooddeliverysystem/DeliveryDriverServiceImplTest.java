package com.example.fooddeliverysystem;

import com.example.fooddeliverysystem.dto.DeliveryDriverDto;
import com.example.fooddeliverysystem.entity.DeliveryDriver;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.DeliveryDriverRepository;
import com.example.fooddeliverysystem.service.impl.DeliveryDriverServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DeliveryDriverServiceImpl Test")
class DeliveryDriverServiceImplTest {

    @Mock
    private DeliveryDriverRepository driverRepository;

    @InjectMocks
    private DeliveryDriverServiceImpl driverService;

    private DeliveryDriver driver;

    @BeforeEach
    void setUp() {
        driver = new DeliveryDriver();
        driver.setDriverId(1);
        driver.setDriverName("John Doe");
    }
    // API — getDriverById(Integer driverId)

    @Nested
    @DisplayName("getDriverById() Tests")
    class GetDriverByIdTests {

        @Test
        @DisplayName("POSITIVE — should return DriverDTO when driver exists")
        void shouldReturnDriver_whenDriverExists() {


            when(driverRepository.findById(1))
                    .thenReturn(Optional.of(driver));


            DeliveryDriverDto result = driverService.getDriverById(1);


            assertThat(result).isNotNull();


            verify(driverRepository, times(1)).findById(1);
        }

        @Test
        @DisplayName("NEGATIVE — should throw exception when driver not found")
        void shouldThrowException_whenDriverNotFound() {
            when(driverRepository.findById(99))
                    .thenReturn(Optional.empty());


            assertThatThrownBy(() -> driverService.getDriverById(99))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Driver")
                    .hasMessageContaining("99");


            verify(driverRepository, times(1)).findById(99);
        }
    }
}