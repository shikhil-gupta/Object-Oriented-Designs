package com.gojek.parkinglot.model;

import com.gojek.parkinglot.constants.ParkingSlotStatus;
import com.gojek.parkinglot.constants.ParkingSlotType;
import lombok.Getter;

@Getter
public class CompactParkingSlot extends ParkingSlot {
    public CompactParkingSlot(final int slotNo, final int parkingSlotCharges) {
        super(slotNo, ParkingSlotStatus.VACCANT, parkingSlotCharges, ParkingSlotType.COMPACT_PARKING_SLOT);
    }
}
