package com.gojek.parkinglot.validation;

public interface UserValidationStrategy {
    Boolean isValidInput(final String action);
}
