package com.example.fooddeliverysystem;

import com.example.fooddeliverysystem.dto.CouponDto;
import com.example.fooddeliverysystem.entity.Coupon;
import com.example.fooddeliverysystem.exception.ResourceNotFoundException;
import com.example.fooddeliverysystem.repository.CouponRepository;
import com.example.fooddeliverysystem.service.impl.CouponServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceImplTest {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    private Coupon activeCoupon;
    private Coupon anotherActiveCoupon;

    @BeforeEach
    void setUp() {
        activeCoupon = new Coupon(
                1,
                "SAVE20",
                BigDecimal.valueOf(20.0),
                LocalDate.now().plusDays(5)
        );

        anotherActiveCoupon = new Coupon(
                2,
                "DEAL10",
                BigDecimal.valueOf(10.0),
                LocalDate.now().plusDays(3)
        );
    }

    @Nested
    @DisplayName("API  — getCouponById()")
    class GetCouponById {

        @Test
        @DisplayName("PositiveTest")
        void positiveTestGetCouponById() {

            when(couponRepository.findById(1))
                    .thenReturn(Optional.of(activeCoupon));

            CouponDto result = couponService.getCouponById(1);

            assertThat(result).isNotNull();
            assertThat(result.getCouponId()).isEqualTo(1);
            assertThat(result.getCouponCode()).isEqualTo("SAVE20");

            assertThat(result.getDiscountAmount())
                    .isEqualByComparingTo("20.0");

            assertThat(result.getExpiryDate())
                    .isAfter(LocalDate.now());

            verify(couponRepository, times(1)).findById(1);
        }

        @Test
        @DisplayName("NegativeTest")
        void negativeTestGetcouponById() {

            when(couponRepository.findById(99))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> couponService.getCouponById(99))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Coupon")
                    .hasMessageContaining("99");

            verify(couponRepository, times(1)).findById(99);
        }


        @Test
        @DisplayName("POSITIVE TestCase for Active Coupon")
        void positive_returnsActiveCouponDTOList() {

            when(couponRepository.findActiveCoupons())
                    .thenReturn(List.of(activeCoupon, anotherActiveCoupon));

            List<CouponDto> result = couponService.getActiveCoupons();

            assertThat(result).hasSize(2);

            assertThat(result.get(0).getCouponId()).isEqualTo(1);
            assertThat(result.get(0).getCouponCode()).isEqualTo("SAVE20");
            assertThat(result.get(0).getDiscountAmount())
                    .isEqualByComparingTo(BigDecimal.valueOf(20.0));
            assertThat(result.get(0).getExpiryDate()).isAfter(LocalDate.now());

            assertThat(result.get(1).getCouponId()).isEqualTo(2);
            assertThat(result.get(1).getCouponCode()).isEqualTo("DEAL10");
            assertThat(result.get(1).getDiscountAmount())
                    .isEqualByComparingTo(BigDecimal.valueOf(10.0));

            verify(couponRepository, times(1)).findActiveCoupons();
        }

        @Test
        @DisplayName("NEGATIVE :-TestCase For Non-Active Coupon")
        void negative_returnsEmptyListWhenNoActiveCoupons() {

            when(couponRepository.findActiveCoupons()).thenReturn(List.of());

            List<CouponDto> result = couponService.getActiveCoupons();

            assertThat(result).isNotNull();
            assertThat(result).isEmpty();

            verify(couponRepository, times(1)).findActiveCoupons();
        }
    }
}