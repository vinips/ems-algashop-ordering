package com.algawors.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ZipCodeTest {

    private String zipCodeNumbers = "15896";

    @Nested
    class ZipCodeConstructor {

        @Test
        void given_newZipCode_whenValueIsNotNull_shouldCreateNewZipCode() {
            ZipCode zipCode = new ZipCode(zipCodeNumbers);

            Assertions.assertThat(zipCode.value()).isEqualTo(zipCodeNumbers);
        }

        @Test
        void given_newZipCode_whenValueIsNull_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> new ZipCode(null));
        }

        @Test
        void given_newZipCode_whenValueIsBlank_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new ZipCode(""));
        }

        @Test
        void given_newZipCode_whenValueHasInvalidLength_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new ZipCode("156489"));
        }

    }

    @Test
    void given_newZipCode_whenCallToString_shouldReturnValue() {
        ZipCode zipCode = new ZipCode(zipCodeNumbers);

        Assertions.assertThat(zipCode).hasToString(zipCodeNumbers);
    }

}