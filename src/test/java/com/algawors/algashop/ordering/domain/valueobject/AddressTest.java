package com.algawors.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class AddressTest {

    @Test
    void given_newAddress_whenAllAttributesAreCorrect_shouldCreateNewAddress() {
        String street = "Bourbon Street";
        String complement = "House 1";
        String neighborhood = "Arthur Ville";
        String number = "195";
        String city = "Raleigh";
        String state = "North Carolina";
        ZipCode zipCode = new ZipCode("12345");

        Address address = Address.builder()
                .street(street)
                .complement(complement)
                .neighborhood(neighborhood)
                .number(number)
                .city(city)
                .state(state)
                .zipCode(zipCode)
                .build();

        Assertions.assertThat(address.street()).isEqualTo(street);
        Assertions.assertThat(address.complement()).isEqualTo(complement);
        Assertions.assertThat(address.neighborhood()).isEqualTo(neighborhood);
        Assertions.assertThat(address.number()).isEqualTo(number);
        Assertions.assertThat(address.city()).isEqualTo(city);
        Assertions.assertThat(address.state()).isEqualTo(state);
        Assertions.assertThat(address.zipCode().value()).isEqualTo(zipCode.value());

    }

    @Nested
    class StreetAddress {

        @Test
        void given_newAddress_whenStreetIsNotNull_shouldCreateNewAddress() {
            String street = "Jackson Street";
            Address address = createNewAddress().toBuilder().street(street).build();

            Assertions.assertThat(address.street()).isEqualTo(street);
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenStreetIsNull_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> address.toBuilder().street(null).build());
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenStreetIsBlank_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> address.toBuilder().street("").build());
        }

    }

    @Nested
    class ComplementAddress {

        @ParameterizedTest
        @ValueSource(strings = {"House 5", ""})
        void given_newAddress_whenComplementIsNotNullOrBlank_shouldCreateNewAddress(String arg) {
            newAddressConstructor(arg);
        }

        @Test
        void given_newAddress_whenComplementIsNull_shouldCreateNewAddress() {
            String complement = null;
            newAddressConstructor(complement);
        }

        private void newAddressConstructor(String complement) {
            Address address = createNewAddress().toBuilder().complement(complement).build();

            Assertions.assertThat(address.complement()).isEqualTo(complement);
        }

    }

    @Nested
    class NeighborhoodAddress {

        @Test
        void given_newAddress_whenNeighborhoodIsNotNull_shouldCreateNewAddress() {
            String neighborhood = "Joana Bride";
            Address address = createNewAddress().toBuilder().neighborhood(neighborhood).build();

            Assertions.assertThat(address.neighborhood()).isEqualTo(neighborhood);
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenNeighborhoodIsNull_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> address.toBuilder().neighborhood(null).build());
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenNeighborhoodIsBlank_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> address.toBuilder().neighborhood("").build());
        }

    }

    @Nested
    class NumberAddress {

        @Test
        void given_newAddress_whenNumberIsNotNull_shouldCreateNewAddress() {
            String number = "1252";
            Address address = createNewAddress().toBuilder().number(number).build();

            Assertions.assertThat(address.number()).isEqualTo(number);
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenNumberIsNull_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> address.toBuilder().number(null).build());
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenNumberIsBlank_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> address.toBuilder().number("").build());
        }

    }

    @Nested
    class CityAddress {

        @Test
        void given_newAddress_whenCityIsNotNull_shouldCreateNewAddress() {
            String city = "Durham";
            Address address = createNewAddress().toBuilder().city(city).build();

            Assertions.assertThat(address.city()).isEqualTo(city);
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenCityIsNull_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> address.toBuilder().city(null).build());
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenCityIsBlank_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> address.toBuilder().city("").build());
        }

    }

    @Nested
    class StateAddress {

        @Test
        void given_newAddress_whenStateIsNotNull_shouldCreateNewAddress() {
            String state = "Durham";
            Address address = createNewAddress().toBuilder().state(state).build();

            Assertions.assertThat(address.state()).isEqualTo(state);
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenStateIsNull_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> address.toBuilder().state(null).build());
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenStateIsBlank_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> address.toBuilder().state("").build());
        }

    }

    @Nested
    class ZipCodeAddress {

        @Test
        void given_newAddress_whenZipCodeIsNotNull_shouldCreateNewAddress() {
            ZipCode zipCode = new ZipCode("10125");
            Address address = createNewAddress().toBuilder().zipCode(zipCode).build();

            Assertions.assertThat(address.zipCode()).isEqualTo(zipCode);
        }

        @Test
        @SuppressWarnings("squid:S5778")
        void given_newAddress_whenZipCodeIsNull_shouldGenerateException() {
            Address address = createNewAddress();

            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> address.toBuilder().zipCode(null).build());
        }
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