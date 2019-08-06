package com.elevator.design.elevator;

import com.elevator.design.Request;
import com.elevator.design.constants.Directions;
import com.elevator.design.constants.States;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Elevator implements Runnable {

    private int elevatorId;
    private int startRange;
    private int endRange;

    private AtomicInteger currentFloor;

    private Directions currentDirections;

    private int totalCapacityAllowed;

    private States currentState;

    private HashMap<Integer, Request> floorToRequestMap;

    public Elevator() {
        floorToRequestMap = new HashMap<>();
        currentDirections = Directions.DEFAULT;
        currentState = States.WAITING;
    }

    public void run() {

        while (true) {
            try {
                synchronized (this) {
                    if (currentState == States.WAITING) {
                        currentDirections = Directions.DEFAULT;
                        wait();
                    }
                }

                if (currentState == States.STOP) {
                    break;
                }
                while (currentState == States.MOVING) {

                    boolean stopToCurrenFloor = false;
                    synchronized (floorToRequestMap) {
                        if (floorToRequestMap.get(currentFloor.get()) != null) {
                            floorToRequestMap.put(currentFloor.get(), null);
                            stopToCurrenFloor = true;
                        }
                    }
                    if (stopToCurrenFloor) {
                        synchronized (this) {
                            currentState = States.OPENDOOR;
                        }
                        openDoor();
                        synchronized (this) {
                            currentState = States.CLOSEDDOOR;
                        }
                        closeDoor();
                    }

                    if (!canMoveUp() || !canMoveDown()) {
                        synchronized (this) {
                            currentState = States.WAITING;
                            currentDirections = Directions.DEFAULT;
                        }
                        break;
                    }
                    waitingToReachNextFloor();

                }
            } catch (Exception ex) {
                currentDirections = Directions.DEFAULT;
                currentState = States.WAITING;
            }
        }
    }

    private void waitingToReachNextFloor() throws InterruptedException {
        Thread.sleep(10000);
        if (currentDirections == Directions.UP) {
            currentFloor.incrementAndGet();
        } else {
            currentFloor.decrementAndGet();
        }
    }

    private void openDoor() throws InterruptedException {
        //send event to lift console to open door
        Thread.sleep(10000);
    }

    private void closeDoor() throws InterruptedException {
        //send event to lift console to open door
        Thread.sleep(10000);
    }

    private boolean canMoveUp() {

        if (currentFloor.get() == endRange) {
            return false;
        }

        synchronized (floorToRequestMap) {
            if (currentDirections == Directions.UP || currentDirections == Directions.DEFAULT) {
                for (int i = currentFloor.get(); i <= endRange; i++) {
                    if (floorToRequestMap.get(i) != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean canMoveDown() {
        if (currentFloor.get() == startRange) {
            return false;
        }
        synchronized (floorToRequestMap) {
            if (currentDirections == Directions.DOWN || currentDirections == Directions.DEFAULT) {
                for (int i = currentFloor.get(); i >= startRange; i--) {
                    if (floorToRequestMap.get(i) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public synchronized void addRequestFromOuterControl(Request request) {

        if (!isValidRequest(request)) {
            return;
        }

        if (currentState == States.MOVING) {
            synchronized (floorToRequestMap) {
                floorToRequestMap.put(request.getRequestedFloor(), request);
            }
            return;
        }

        if (currentState == States.WAITING) {
            if (request.getRequestedFloor() <= currentFloor.get() && request.getRequestedDirections() == Directions.DOWN) {
                currentDirections = Directions.DOWN;
                currentState = States.MOVING;
                notify();
            } else if (request.getRequestedFloor() >= currentFloor.get() &&
                    request.getRequestedDirections() == Directions.UP) {
                currentDirections = Directions.UP;
                currentState = States.MOVING;
                notify();
            }
        }

    }

    private boolean isValidRequest(Request request) {
        if (request.getRequestedFloor() >= startRange && request.getRequestedFloor() <= endRange) {
            return true;
        } else {
            return false;
        }
    }

}
