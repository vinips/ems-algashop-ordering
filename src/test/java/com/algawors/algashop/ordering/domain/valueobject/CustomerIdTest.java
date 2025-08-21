package com.algawors.algashop.ordering.domain.valueobject;


import com.algawors.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CustomerIdTest {

    @Nested
    class CustomerIdConstructor {

        @Test
        void given_newCustomerId_whenNoParams_shouldCreateCustomerId() {
            CustomerId customerId = new CustomerId();

            Assertions.assertThat(customerId.value()).isNotNull();
        }

        @Test
        void given_newCustomerId_whenValueIsNotNull_shouldCreateCustomerId() {
            UUID uuid = IdGenerator.generateTimeBasedUUID();
            CustomerId customerId = new CustomerId(uuid);

            Assertions.assertThat(customerId.value()).isEqualTo(uuid);
        }

        @Test
        void given_newCustomerId_whenValueIsNull_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> new CustomerId(null));
        }
    }

    @Test
    void given_newCustomerId_whenCallToString_shouldReturnValue() {
        UUID uuid = IdGenerator.generateTimeBasedUUID();
        CustomerId customerId = new CustomerId(uuid);

        Assertions.assertThat(customerId).hasToString(uuid.toString());
    }

}