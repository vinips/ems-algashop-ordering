package com.algawors.algashop.ordering;

import com.algawors.algashop.ordering.domain.entity.Customer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

class CustomerTest {

    @Test
     void testingCustomer() {
        Customer customer = new Customer(
                UUID.randomUUID(),
                "Jhon Doe",
                LocalDate.of(1992, 12, 24),
                "jhon.doe@email.com",
                "478-585-2504",
                "255-08-0758",
                true,
                OffsetDateTime.now()
        );

        customer.addLoyaltyPoints(20);

    }
}
