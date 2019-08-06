package com.gojek.parkinglot.validation;

import com.gojek.parkinglot.constants.Color;

public enum UserActionValidationStrategy implements UserValidationStrategy {

    CREATE_PARKING_LOT("create_parking_lot") {
        public Boolean isValidInput(final String action) {

            String[] values = action.split(" ");
            Boolean isValid = Boolean.TRUE;
            if (values.length != 2) {
                isValid &= Boolean.FALSE;
            }
            try {
                Integer.valueOf(values[1]);
            } catch (Exception ex) {
                isValid &= Boolean.FALSE;
            }
            return isValid;
        }
    },

    LEAVE("leave") {
        public Boolean isValidInput(final String action) {

            String[] values = action.split(" ");
            Boolean isValid = Boolean.TRUE;
            if (values.length != 2) {
                isValid &= Boolean.FALSE;
            }
            try {
                Integer.valueOf(values[1]);
            } catch (Exception ex) {
                isValid &= Boolean.FALSE;
            }
            return isValid;
        }
    },

    PARK("park") {
        public Boolean isValidInput(final String action) {

            String[] values = action.split(" ");
            Boolean isValid = Boolean.TRUE;
            if (values.length != 3) {
                isValid &= Boolean.FALSE;
            }
            try {
                Color.getColorByValue(values[2]);
            } catch (Exception ex) {
                isValid &= Boolean.FALSE;
            }
            return isValid;
        }
    },

    STATUS("status") {
        public Boolean isValidInput(final String action) {
            return Boolean.TRUE;
        }
    },

    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour") {
        public Boolean isValidInput(final String action) {
            String[] values = action.split(" ");
            Boolean isValid = Boolean.TRUE;
            if (values.length != 2) {
                isValid &= Boolean.FALSE;
            }
            try {
                Color.getColorByValue(values[1]);
            } catch (Exception ex) {
                isValid &= Boolean.FALSE;
            }
            return isValid;

        }
    },

    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour") {
        public Boolean isValidInput(final String action) {
            String[] values = action.split(" ");
            Boolean isValid = Boolean.TRUE;
            if (values.length != 2) {
                isValid &= Boolean.FALSE;
            }
            try {
                Color.getColorByValue(values[1]);
            } catch (Exception ex) {
                isValid &= Boolean.FALSE;
            }
            return isValid;

        }
    },

    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number") {
        public Boolean isValidInput(final String action) {
            String[] values = action.split(" ");
            Boolean isValid = Boolean.TRUE;
            if (values.length != 2) {
                isValid &= Boolean.FALSE;
            }
            return isValid;
        }

    };

    private String userInputActionString;

    UserActionValidationStrategy(String userInputActionString) {
        this.userInputActionString = userInputActionString;
    }

}
