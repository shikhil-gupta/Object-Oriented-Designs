package com.gojek.parkinglot.strategy;

import com.gojek.parkinglot.constants.ParkingErrorCodes;
import com.gojek.parkinglot.managers.MultiBuildingAndStoryParkingManager;
import com.gojek.parkinglot.managers.MultiStoryParkingManager;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.pojo.ParkingResponsePojo;

import java.util.Map;

public class FirstFillStrategy implements IAssignmentStrategy {
    @Override
    public ParkingResponsePojo assignSlot(final Vehicle vehicle, final MultiBuildingAndStoryParkingManager multiBuildingAndStoryParkingManager) {

        ParkingResponsePojo parkingResponsePojo = new ParkingResponsePojo();
        if(multiBuildingAndStoryParkingManager.getAvailibility().get() <= 0) {
            parkingResponsePojo.setErrorCode(ParkingErrorCodes.PARKING_FULL.getErrorCode());
            return parkingResponsePojo;
        }
        ParkingResponsePojo pojo = null;
        for (Map.Entry<Integer, MultiStoryParkingManager> buildingNoToParkingBuildingMap: multiBuildingAndStoryParkingManager.getBuildingNoToParkingBuildingMap().entrySet()) {

            pojo = buildingNoToParkingBuildingMap.getValue().park(vehicle);
            if(pojo.getErrorCode() == ParkingErrorCodes.PARKING_SUCCESS.getErrorCode()) {
                return pojo;
            }

        }
        return pojo;
    }
}
