package com.algawors.algashop.ordering.domain.valueobject;

import com.algawors.algashop.ordering.domain.exception.ErrorMessages;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public record BirthDate(LocalDate value) {

    @SuppressWarnings("squid:S6207")
    public BirthDate(LocalDate value) {
        Objects.requireNonNull(value, ErrorMessages.VALIDATION_ERROR_BIRTHDATE_IS_NULL);

        if (value.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST);
        }
        this.value = value;
    }

    public Integer age() {
        LocalDate currentDate = LocalDate.now();

        Period agePeriod = Period.between(this.value, currentDate);

        return agePeriod.getYears();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
