package com.application.tollManagement.models;

import com.application.tollManagement.constants.VehicleType;
import lombok.Getter;

@Getter
public abstract class Vehicle {

    private String registrationNo;
    private VehicleType vehicleType;

    public Vehicle(final String registrationNo, final VehicleType vehicleType) {
        this.registrationNo = registrationNo;
        this.vehicleType = vehicleType;
    }

}