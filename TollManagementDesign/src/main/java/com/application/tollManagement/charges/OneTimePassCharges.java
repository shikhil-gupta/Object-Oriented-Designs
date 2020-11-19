package com.application.tollManagement.charges;

import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.constants.VehicleType;

import java.util.concurrent.ConcurrentHashMap;

public class OneTimePassCharges extends PassCharges {

    private ConcurrentHashMap<VehicleType, Double> oneTimeChargesPass;

    public OneTimePassCharges() {
        super(TollPassType.ONE_TIME);
        oneTimeChargesPass = new ConcurrentHashMap<>();
    }

    @Override
    public double getCharges(VehicleType vehicleType) {
        return oneTimeChargesPass.get(vehicleType);
    }
}
