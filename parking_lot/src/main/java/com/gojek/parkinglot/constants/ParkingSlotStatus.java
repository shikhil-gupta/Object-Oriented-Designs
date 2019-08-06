package com.gojek.parkinglot.constants;

public enum ParkingSlotStatus {
    VACCANT("vaccant"), OCCUPIED("occupied");

    private String status;

    ParkingSlotStatus(final String status) {
        this.status = status;
    }

}
