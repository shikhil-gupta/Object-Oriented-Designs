package com.elevator.design.service;

import com.elevator.design.Request;
import com.elevator.design.elevator.ElevatorManager;

import java.util.HashMap;

public class ElevatorService implements IElevatorService {

    private HashMap<Integer, ElevatorManager> elevatorIdToManager;

    public void placeRequest(Request request) {
        ElevatorManager elevatorManager = elevatorIdToManager.get(request.getElevatorId());
        elevatorManager.placeRequest(request);
    }
}
