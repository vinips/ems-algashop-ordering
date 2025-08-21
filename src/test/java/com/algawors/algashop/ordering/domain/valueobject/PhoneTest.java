package com.algawors.algashop.ordering.domain.valueobject;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PhoneTest {

    @Nested
    class PhoneConstructor {

        @Test
        void given_newPhone_whenValueIsNotNull_shouldCreateNewPhone() {
            String phoneNumbers = "478-585-2504";
            Phone phone = new Phone(phoneNumbers);

            Assertions.assertThat(phone.value()).isEqualTo(phoneNumbers);
        }

        @Test
        void given_newPhone_whenValueIsNull_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> new Phone(null));
        }

        @Test
        void given_newPhone_whenValueIsBlank_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new Phone(""));
        }

    }

    @Test
    void given_newPhone_whenCallToString_shouldReturnValue() {
        String phoneNumbers = "478-585-2504";
        Phone phone = new Phone(phoneNumbers);

        Assertions.assertThat(phone.toString()).hasToString(phoneNumbers);
    }


}