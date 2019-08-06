package com.gojek.parkinglot.model;

import com.gojek.parkinglot.constants.ParkingSlotStatus;
import com.gojek.parkinglot.constants.ParkingSlotType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Optional;

@Getter
public abstract class ParkingSlot {
    private int slotNo;

    @Setter
    private ParkingSlotStatus status;

    @Setter
    private Optional<Vehicle> vehicleOptional;

    @Setter
    private Optional<Timestamp>  lastModifiedTimeStamp;

    private int parkingSlotCharges;

    private ParkingSlotType parkingSlotType;

    ParkingSlot(final int slotNo, final ParkingSlotStatus slotStatus, final int parkingSlotCharges, final ParkingSlotType parkingSlotType) {
        this.slotNo = slotNo;
        this.status = slotStatus;
        this.parkingSlotCharges = parkingSlotCharges;
        this.vehicleOptional = Optional.empty();
        this.parkingSlotType = parkingSlotType;
        this.lastModifiedTimeStamp = Optional.empty();
    }

}
