package com.elevator.design.elevator;

import com.elevator.design.Request;

public interface IElevatorManager {
    void startElevator();
    void stopElevator();

    void placeRequest(Request request);

}
