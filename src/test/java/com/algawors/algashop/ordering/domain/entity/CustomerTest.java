package com.algawors.algashop.ordering.domain.entity;


import com.algawors.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {

    @Test
    void given_invalidEmail_whenTryCreateCostumer_shouldGenerateException() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Customer(
                            IdGenerator.generateTimeBasedUUID(),
                            "Jhon Doe",
                            LocalDate.of(1992, 12, 24),
                            "invalid",
                            "255-08-0758",
                            "478-585-2504",
                            false,
                            OffsetDateTime.now()
                    );
                });
    }

    @Test
    void given_invalidEmail_whenTryUpdateCostumerEmail_shouldGenerateException() {
        Customer customer = createNewCustomer();

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                            customer.changeEmail("invalid");
                        }
                );
    }

    @Test
    void given_unarchivedCustomer_whenArchive_shouldAnonymize() {
        Customer customer = createNewCustomer();

        customer.archive();

        Assertions.assertWith(customer,
                c -> Assertions.assertThat(c.fullName()).isEqualTo("Anonymous"),
                c -> Assertions.assertThat(c.email()).isNotEqualTo("jhon.doe@gmail.com"),
                c -> Assertions.assertThat(c.phone()).isEqualTo("000-000-0000"),
                c -> Assertions.assertThat(c.document()).isEqualTo("000-00-0000"),
                c -> Assertions.assertThat(c.birthDate()).isNull()
        );
    }

    private Customer createNewCustomer() {
        return new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Jhon Doe",
                LocalDate.of(1992, 12, 24),
                "jhon.doe@gmail.com",
                "255-08-0758",
                "478-585-2504",
                false,
                OffsetDateTime.now()
        );
    }

}