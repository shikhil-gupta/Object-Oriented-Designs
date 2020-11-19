package com.application.tollManagement.charges;

import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.constants.VehicleType;

import java.util.concurrent.ConcurrentHashMap;

public class ReturnInDayPassCharges extends PassCharges {

    private ConcurrentHashMap<VehicleType, Double> returnInDayPassCharges;

    public ReturnInDayPassCharges() {
        super(TollPassType.RETURN_PASS);
        returnInDayPassCharges = new ConcurrentHashMap<>();
    }

    @Override
    public double getCharges(VehicleType vehicleType) {
        return returnInDayPassCharges.get(vehicleType);
    }
}
