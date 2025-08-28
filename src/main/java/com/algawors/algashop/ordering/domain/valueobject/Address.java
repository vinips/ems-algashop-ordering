package com.algawors.algashop.ordering.domain.valueobject;

import com.algawors.algashop.ordering.domain.exception.ErrorMessages;
import com.algawors.algashop.ordering.domain.utility.FieldValidations;
import lombok.Builder;

import java.util.Objects;

@Builder(toBuilder = true)
public record Address(
        String street,
        String complement,
        String neighborhood,
        String number,
        String city,
        String state,
        ZipCode zipCode) {

    public Address {
        FieldValidations.requiresNonBlank(street, ErrorMessages.VALIDATION_ERROR_STREET_IS_BLANK_OR_NULL);
        FieldValidations.requiresNonBlank(neighborhood, ErrorMessages.VALIDATION_ERROR_NEIGHBORHOOD_IS_BLANK_OR_NULL);
        FieldValidations.requiresNonBlank(number, ErrorMessages.VALIDATION_ERROR_NUMBER_IS_BLANK_OR_NULL);
        FieldValidations.requiresNonBlank(city, ErrorMessages.VALIDATION_ERROR_CITY_IS_BLANK_OR_NULL);
        FieldValidations.requiresNonBlank(state, ErrorMessages.VALIDATION_ERROR_STATE_IS_BLANK_OR_NULL);

        Objects.requireNonNull(zipCode);
    }
}
