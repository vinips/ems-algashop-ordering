package com.algawors.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

class BirthDateTest {

    @Nested
    class BirthDateConstructor {

        @Test
        void given_newBirthDate_whenValueIsNotNull_shouldCreateNewBirthDate() {
            LocalDate now = LocalDate.now();
            BirthDate birthDate = new BirthDate(now);

            Assertions.assertThat(birthDate.value()).isEqualTo(now);
        }

        @Test
        void given_newBirthDate_whenValueIsNull_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> new BirthDate(null));
        }

        @Test
        void given_newBirthDate_whenValueIsInFuture_shouldGenerateException() {
            LocalDate localDateFuture = LocalDate.of(2026, 2, 22);
            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new BirthDate(localDateFuture));
        }

    }

    @Test
    void given_correctBirthDate_whenCalculateAge_shouldReturnCorrectAge() {
        LocalDate myBirthDate = LocalDate.of(1992, 12, 24);
        BirthDate birthDate = new BirthDate(myBirthDate);
        Period agePeriod = null;

        Integer age = birthDate.age();

        agePeriod = Period.between(myBirthDate, LocalDate.now());
        Assertions.assertThat(age).isEqualTo(agePeriod.getYears());
    }



}