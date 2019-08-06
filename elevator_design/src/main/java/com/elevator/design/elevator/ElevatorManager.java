package com.elevator.design.elevator;

import com.elevator.design.Request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ElevatorManager implements IElevatorManager {

    private Elevator elevator;
    private ExecutorService executorService;

    public ElevatorManager() {
        elevator = new Elevator();
        elevator.setElevatorId(1);
        elevator.setStartRange(1);
        elevator.setEndRange(10);
        elevator.setCurrentFloor(new AtomicInteger(1));
    }


    public void startElevator() {
        executorService= Executors.newFixedThreadPool(1);
        executorService.submit(elevator);
    }

    public void stopElevator() {
        executorService.shutdown();
    }

    public void placeRequest(Request request) {
        elevator.addRequestFromOuterControl(request);
    }
}
