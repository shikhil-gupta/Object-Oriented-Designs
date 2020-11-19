package com.application.tollManagement.models;

import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.constants.VehicleType;

public class ReturnTollPass extends TollPass {

    public ReturnTollPass(double charges, Integer tollBoothID, String regNo, VehicleType vehicleType, TollPassType tollPassType) {
        super(charges, tollBoothID, regNo, vehicleType, tollPassType);
    }

    @Override
    public boolean IsValidTollPass() {
        return false;
    }
}
