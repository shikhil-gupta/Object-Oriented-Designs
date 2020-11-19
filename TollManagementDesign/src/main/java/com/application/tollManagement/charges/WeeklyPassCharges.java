package com.application.tollManagement.charges;

import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.constants.VehicleType;

import java.util.concurrent.ConcurrentHashMap;

public class WeeklyPassCharges extends PassCharges
{
    private ConcurrentHashMap<VehicleType, Double> weeklyPassCharges;

    public WeeklyPassCharges() {
        super(TollPassType.WEEKLY_PASS);
        weeklyPassCharges = new ConcurrentHashMap<>();
    }

    @Override
    public double getCharges(VehicleType vehicleType) {
        return weeklyPassCharges.get(vehicleType);
    }
}
