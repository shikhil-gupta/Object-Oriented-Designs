package com.application.tollManagement.models;

import com.application.tollManagement.constants.TollPassType;
import com.application.tollManagement.constants.VehicleType;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
public abstract class TollPass {

    private double charges;
    private Integer tollBoothID;
    private String regNo;
    private Timestamp issueAtTimeStamp;
    private VehicleType vehicleType;
    private TollPassType tollPassType;

    protected TollPass(final double charges, final  Integer tollBoothID, final String regNo, final VehicleType vehicleType, final TollPassType tollPassType) {
        this.charges = charges;
        this.tollBoothID = tollBoothID;
        this.regNo = regNo;
        Date date = new Date();
        this.issueAtTimeStamp = new Timestamp(date.getTime());
        this.vehicleType = vehicleType;
        this.tollPassType = tollPassType;
    }

    public abstract boolean IsValidTollPass();
}
