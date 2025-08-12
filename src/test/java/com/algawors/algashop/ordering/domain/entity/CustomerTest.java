package com.algawors.algashop.ordering.domain.entity;


import com.algawors.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algawors.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {

    @Nested
    class InvalidEmailCustomer {
        @Test
        void given_invalidEmail_whenTryCreateCostumer_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(this::createNewCustomerInvalidEmail);
        }

        @Test
        void given_invalidEmail_whenTryUpdateCostumerEmail_shouldGenerateException() {
            Customer customer = createNewCustomerPartial();

            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> {
                                customer.changeEmail("invalid");
                            }
                    );
        }

        private Customer createNewCustomerInvalidEmail() {
            return new Customer(
                    IdGenerator.generateTimeBasedUUID(),
                    "Jhon Doe",
                    LocalDate.of(1992, 12, 24),
                    "invalid",
                    "255-08-0758",
                    "478-585-2504",
                    false,
                    OffsetDateTime.now()
            );
        }
    }

    @Nested
    class ArchiveCustomer {
        @Test
        void given_unarchivedCustomer_whenArchive_shouldAnonymize() {
            Customer customer = createNewCustomerPartial();

            customer.archive();

            Assertions.assertWith(customer,
                    c -> Assertions.assertThat(c.fullName()).isEqualTo("Anonymous"),
                    c -> Assertions.assertThat(c.email()).isNotEqualTo("jhon.doe@gmail.com"),
                    c -> Assertions.assertThat(c.phone()).isEqualTo("000-000-0000"),
                    c -> Assertions.assertThat(c.document()).isEqualTo("000-00-0000"),
                    c -> Assertions.assertThat(c.birthDate()).isNull(),
                    c -> Assertions.assertThat(c.isPromotionNotificationsAllowed()).isFalse()
            );
        }

        @Test
        void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
            Customer customer = createNewCustomerPartial();

            customer.archive();

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(customer::archive);

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(() -> customer.changeName("Doe Jhon"));

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(() -> customer.changeEmail("doe.jhon@gmail.com"));

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(() -> customer.changePhone("111-111-1111"));

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(customer::enablePromotionNotifications);

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(customer::disablePromotionNotifications);


        }
    }

    @Nested
    class LoyaltyPointsCostumer {

        @Test
        void given_newCustomer_whenAddValidLoyaltyPoints_shouldSumPoints() {
            Customer customer = createNewCustomerPartial();
            customer.addLoyaltyPoints(15);
            customer.addLoyaltyPoints(30);

            Assertions.assertThat(customer.loyaltyPoints()).isEqualTo(45);
        }

        @Test
        void given_newCustomer_whenAddInvalidLoyaltyPoints_shouldGenerateException() {
            Customer customer = createNewCustomerPartial();

            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() ->
                            customer.addLoyaltyPoints(-15));
        }

    }



    private Customer createNewCustomerPartial() {
        return new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Jhon Doe",
                LocalDate.of(1992, 12, 24),
                "jhon.doe@gmail.com",
                "255-08-0758",
                "478-585-2504",
                true,
                OffsetDateTime.now()
        );
    }

}