package com.algawors.algashop.ordering.domain.entity;


import com.algawors.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algawors.algashop.ordering.domain.valueobject.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {

    @Nested
    class InvalidEmailCustomer {
        @Test
        void given_invalidEmail_whenTryCreateCustomer_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(this::createNewCustomerInvalidEmail);
        }

        private Customer createNewCustomerInvalidEmail() {
            return Customer.brandNew(
                    new FullName("Jhon", "Doe"),
                    new BirthDate(LocalDate.of(1992, 12, 24)),
                    new Email("invalid"),
                    new Document("255-08-0758"),
                    new Phone("478-585-2504"),
                    false,
                    createNewAddress()
            );
        }
    }

    @Nested
    class ArchiveCustomer {
        @Test
        void given_unarchivedCustomer_whenArchive_shouldAnonymize() {
            Customer customer = createNewCustomerPartial();
            Address validAddress = customer.address();

            customer.archive();


            Assertions.assertWith(customer,
                    c -> Assertions.assertThat(c.fullName()).hasToString("Anonymous Anonymous"),
                    c -> Assertions.assertThat(c.email().value()).isNotEqualTo("jhon.doe@gmail.com"),
                    c -> Assertions.assertThat(c.phone()).hasToString("000-000-0000"),
                    c -> Assertions.assertThat(c.document()).hasToString("000-00-0000"),
                    c -> Assertions.assertThat(c.birthDate()).isNull(),
                    c -> Assertions.assertThat(c.isPromotionNotificationsAllowed()).isFalse(),
                    c -> Assertions.assertThat(c.address()).isNotEqualTo(validAddress)

            );
        }

        @Test
        void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
            Customer customer = createExistingAnonymousCustomer();
            FullName originalFullName = new FullName("Doe", "Jhon");
            Email email = new Email("doe.jhon@gmail.com");
            Phone phone = new Phone("111-111-1111");
            Address validAddress = customer.address();

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(customer::archive);

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(() -> customer.changeName(originalFullName));

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(() -> customer.changeEmail(email));

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(() -> customer.changePhone(phone));

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(customer::enablePromotionNotifications);

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(customer::disablePromotionNotifications);

            Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                    .isThrownBy(() -> customer.changeAddress(validAddress));
        }
    }

    @Nested
    class LoyaltyPointsCustomer {

        @Test
        void given_newCustomer_whenAddValidLoyaltyPoints_shouldSumPoints() {
            Customer customer = createNewCustomerPartial();
            customer.addLoyaltyPoints(new LoyaltyPoints(15));
            customer.addLoyaltyPoints(new LoyaltyPoints(30));

            LoyaltyPoints totalLoyaltyPoints = new LoyaltyPoints(45);

            Assertions.assertThat(customer.loyaltyPoints()).isEqualTo(totalLoyaltyPoints);
        }
    }

    private Customer createNewCustomerPartial() {
        return Customer.brandNew(
                new FullName("Jhon", "Doe"),
                new BirthDate(LocalDate.of(1992, 12, 24)),
                new Email("jhon.doe@gmail.com"),
                new Document("255-08-0758"),
                new Phone("478-585-2504"),
                true,
                createNewAddress()
        );
    }

    private Customer createExistingAnonymousCustomer() {
        return Customer.existing(
                new CustomerId(),
                new FullName("Anonymous", "Anonymous"),
                null,
                new Email("anonymous@anonymous.com"),
                new Document("000-00-0000"),
                new Phone("000-000-0000"),
                false,
                true,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                new LoyaltyPoints(10),
                createNewAddress()
        );
    }

    private Address createNewAddress() {
        ZipCode zipCode = new ZipCode("12345");
        return Address.builder()
                .street("Bourbon Street")
                .complement("House 1")
                .neighborhood("Arthur Ville")
                .number("195")
                .city("Raleigh")
                .state("North Carolina")
                .zipCode(zipCode)
                .build();
    }

}