package com.gojek.parkinglot.utils;

import com.gojek.parkinglot.constants.ParkingErrorCodes;
import com.gojek.parkinglot.pojo.StatusPojo;

import java.util.List;

public class FormattedOutput {

    public static void printStringOutputAsCommaSeprated(List<String> result) {

        if (result.size() == 0) {
            return;
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void printIntegerOutputAsCommaSeprated(final List<Integer> result) {

        if (result.size() == 0) {
            return;
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i).intValue());
            if (i < result.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void printErrorMessage(final int errorCode) {

        ParkingErrorCodes parkingErrorCodes = ParkingErrorCodes.getParkingErrorByValue(errorCode);
        if(parkingErrorCodes.equals(ParkingErrorCodes.PARKING_FULL)) {
            System.out.println(parkingErrorCodes.getErrorMessage());
        }
    }

    public static void printStatusOfSlots(final List<StatusPojo> result) {
        System.out.println("Slot No.    Registration No    Colour");
        if (result.size() == 0)
            System.out.println("Sorry, parking lot is empty.");
        else {
            for (StatusPojo status : result) {
                System.out.print(status.getSlotNo() +"           ");
                System.out.print(status.getRegistrationNo() +"      ");
                System.out.print(status.getColor());
                System.out.println();
            }
        }
    }
}
