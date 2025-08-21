package com.algawors.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class EmailTest {

    @Nested
    class EmailConstructor {

        @Test
        void given_newEmail_whenValueIsNotNullAndValid_shouldCreateNewEmail() {
            String emailString = "teste@gmail.com";
            Email emailValueObject = new Email(emailString);

            Assertions.assertThat(emailValueObject.value()).isEqualTo(emailString);
        }

        @Test
        void given_newEmail_whenValueIsNotNullAndInvalid_shouldGenerateException() {
            String emailString = "teste@gmail.com@teste";

            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                            .isThrownBy(() -> new Email(emailString));
        }

        @Test
        void given_newEmail_whenValueIsNull_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> new Email(null));
        }

        @Test
        void given_newEmail_whenValueIsBlank_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new Email(""));
        }

    }

    @Test
    void given_newEmail_whenCallToString_shouldReturnValue() {
        String emailString = "teste@gmail.com";
        Email emailValueObject = new Email(emailString);

        Assertions.assertThat(emailValueObject).hasToString(emailString);
    }

}