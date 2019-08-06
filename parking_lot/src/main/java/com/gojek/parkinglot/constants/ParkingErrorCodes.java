package com.gojek.parkinglot.constants;

import lombok.Getter;

@Getter
public enum ParkingErrorCodes {
    PARKING_SUCCESS(0, "Success"), PARKING_FULL(-1, "Sorry, parking lot is full"), PARKING_EMPTY(-2, "Sorry, Parking is empty"),
    PARKING_VEHICLE_ALREADY_PAPRKED(-3, "Sorry, Car already parked"),
    PARKING_SLOT_ALREADY_VACCATED(-4, "Sorry, Parking is already vaccated"),
    PARKING_DOES_NOT_EXISTS(-5, "Sorry, Parking does not exists"),
    PARKING_ALREADY_EXISTS(-6, "Sorry, Parking already exists"),
    PARKING_INVALID_COMMAND(-7, "Sorry, Command is not Valid"),
    PARKING_INVALID_FILE(-8, "Sorry, Invalid file"),
    PARKING_SLOT_NO_NOT_VALID(-9, "Sorry, Slot no does not exists"),
    PARKING_VEHICLE_DOES_NOT_EXISTS(-10,"Not foumd"),
    PARKING_INTERNAL_DEFAULT_ERROR(-11, "Sorry, Unknown error has occured. Please retry again after some time");


    private int errorCode;
    private String errorMessage;

    ParkingErrorCodes(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ParkingErrorCodes getParkingErrorByValue(final int errorCode) {
        for (ParkingErrorCodes parkingErrorCode : ParkingErrorCodes.values()) {
            if (parkingErrorCode.getErrorCode() == errorCode) {
                return parkingErrorCode;
            }
        }
        return PARKING_INTERNAL_DEFAULT_ERROR;
    }

    public static String getParkingErrorMessageByErrorValue(final int errorCode) {
        for (ParkingErrorCodes parkingErrorCode : ParkingErrorCodes.values()) {
            if (parkingErrorCode.getErrorCode() == errorCode) {
                return parkingErrorCode.getErrorMessage();
            }
        }
        return PARKING_INTERNAL_DEFAULT_ERROR.getErrorMessage();
    }

}
