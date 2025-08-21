package com.algawors.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LoyaltyPointsTest {

    @Test
    void shouldGenerateWithValue() {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);
        Assertions.assertThat(loyaltyPoints.value()).isEqualTo(10);
    }

    @Test
    void shouldAddValue() {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);
        loyaltyPoints.add(5);
        Assertions.assertThat(loyaltyPoints.add(5).value()).isEqualTo(15);
    }

    @Test
    void shouldNotAddValue() {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()  -> loyaltyPoints.add(-5));

        Assertions.assertThat(loyaltyPoints.value()).isEqualTo(10);
    }

    @Test
    void shouldNotAddZeroValue() {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()  -> loyaltyPoints.add(0));

        Assertions.assertThat(loyaltyPoints.value()).isEqualTo(10);
    }

    @Test
    void shouldReturnToString() {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);

        Assertions.assertThat(loyaltyPoints).hasToString(String.valueOf(10));
    }

}