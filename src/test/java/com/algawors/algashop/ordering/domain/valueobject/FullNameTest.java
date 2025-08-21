package com.algawors.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FullNameTest {

    @Nested
    class FullNameConstructor {

        @Test
        void given_newFullName_whenFistAndLastNameAreNotNull_shouldCreateNewFullName() {
            String firstName = "Jhon";
            String lastName = "Doe";

            FullName fullName = new FullName(firstName, lastName);

            Assertions.assertThat(fullName.firstName()).isEqualTo(firstName);
            Assertions.assertThat(fullName.lastName()).isEqualTo(lastName);
        }

        @Nested
        class FirstNameOrLastNameIsNull {

            @Test
            void given_newFullName_whenFistNameIsNull_shouldGenerateException() {
                String firstName = null;
                String lastName = "Doe";

                Assertions.assertThatExceptionOfType(NullPointerException.class)
                        .isThrownBy(() -> new FullName(firstName, lastName));

            }

            @Test
            void given_newFullName_whenLastNameIsNull_shouldGenerateException() {
                String firstName = "Jhon";
                String lastName = null;

                Assertions.assertThatExceptionOfType(NullPointerException.class)
                        .isThrownBy(() -> new FullName(firstName, lastName));

            }
        }

        @Nested
        class FirstNameOrLastNameIsBlank {

            @Test
            void given_newFullName_whenFistNameIsBlank_shouldGenerateException() {
                String firstName = "";
                String lastName = "Doe";

                Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                        .isThrownBy(() -> new FullName(firstName, lastName));

            }

            @Test
            void given_newFullName_whenLastNameIsBlank_shouldGenerateException() {
                String firstName = "Jhon";
                String lastName = "";

                Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                        .isThrownBy(() -> new FullName(firstName, lastName));

            }
        }

    }

    @Test
    void given_newFullName_whenCallToString_shouldReturnFirstAndLastName() {
        String firstName = "Jhon";
        String lastName = "Doe";

        FullName fullName = new FullName(firstName, lastName);

        Assertions.assertThat(fullName).hasToString(firstName + " " + lastName);
    }

}