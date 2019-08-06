package com.gojek.parkinglot.model;

import com.gojek.parkinglot.constants.Color;
import lombok.Getter;

@Getter
public abstract class Vehicle {
    private Color color;

    private String registrationNo;

    public Vehicle(final Color color, final String registrationNo) {
        this.color = color;
        this.registrationNo = registrationNo;
    }

}
