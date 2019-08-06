package com.gojek.parkinglot.strategy;

import com.gojek.parkinglot.managers.MultiBuildingAndStoryParkingManager;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.pojo.ParkingResponsePojo;

public interface IAssignmentStrategy {

    ParkingResponsePojo assignSlot(final Vehicle vehicle, final MultiBuildingAndStoryParkingManager multiBuildingAndStoryParkingManager);

}
