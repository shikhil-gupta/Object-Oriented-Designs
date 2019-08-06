package com.gojek.parkinglot.model;

import com.gojek.parkinglot.constants.Color;
import lombok.Getter;

@Getter
public class Car extends Vehicle {
    public Car(final Color color, final String registrationNo) {
        super(color, registrationNo);
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj instanceof Car) {
            return getRegistrationNo().equalsIgnoreCase(((Car) obj).getRegistrationNo()) ? true : false;
        }
        return false;
    }
}
