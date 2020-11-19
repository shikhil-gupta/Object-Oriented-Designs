package com.application.tollManagement.charges;

import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.constants.VehicleType;

public abstract class PassCharges
{
    private TollPassType tollPassType;

    protected PassCharges(final TollPassType tollPassType) {
        this.tollPassType = tollPassType;
    }

    public abstract double getCharges(final VehicleType vehicleType);
}
