package com.elevator.design.elevator;

import com.elevator.design.Request;
import com.elevator.design.constants.Directions;
import com.elevator.design.constants.States;
import com.elevator.design.handler.inner.InnerRequestHandler;
import com.elevator.design.handler.inner.InnerRequestHandlerImpl;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;
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

    private TreeSet<Integer> upDestinationRequest;

    private TreeSet<Integer> downDestinationRequest;

    private InnerRequestHandler innerRequestHandler;

    public Elevator() {
        upDestinationRequest = new TreeSet<>();
        downDestinationRequest = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        currentDirections = Directions.NONE;
        currentState = States.WAITING;
        innerRequestHandler = new InnerRequestHandlerImpl();
        currentFloor = new AtomicInteger(0);
    }

    public void run() {

        while (true) {
            try {
                synchronized (this) {
                    if (currentState == States.WAITING) {
                        currentDirections = Directions.NONE;
                        wait();
                    }
                }

                setDirections();
                if(currentDirections == Directions.UP) {
                    if(currentFloor.intValue() == upDestinationRequest.first()) {
                        openDoor();
                        closeDoor();
                        popUpDestination();
                    }
                }
                else {
                    if(currentFloor.intValue() == downDestinationRequest.first()) {
                        openDoor();
                        closeDoor();
                        popDownDestination();
                    }
                }
                updateCurrentState();
                waitingToReachNextFloor();
            } catch (Exception ex) {
                currentDirections = Directions.NONE;
                currentState = States.WAITING;
            }
        }
    }

    // update status to moving only when state is waiting,
    // we can refactor this code further.
    // This function will be called by Elevator manager to set moving.
    public void updateStatus(States state)
    {
        if(currentState == States.MOVING) {
            return;
        }

        currentState = state;
    }

    private void updateCurrentState() {
        if(!(upDestinationRequest.size() > 0 || downDestinationRequest.size() > 0)) {
            currentState = States.WAITING;
        }
    }

    private void waitingToReachNextFloor() throws InterruptedException {
        Thread.sleep(10000);

        if(currentState == States.MOVING) {
            if (currentDirections == Directions.UP) {
                currentFloor.incrementAndGet();
            } else if(currentDirections == Directions.DOWN) {
                currentFloor.decrementAndGet();
            }
        }
    }

    private void popUpDestination() {
        upDestinationRequest.remove(upDestinationRequest.first());
        if(upDestinationRequest.size() == 0) {
            currentDirections = Directions.NONE;
        }
    }

    private void popDownDestination() {
        downDestinationRequest.remove(downDestinationRequest.first());
        if(downDestinationRequest.size() == 0) {
            currentDirections = Directions.NONE;
        }
    }
    private void openDoor() throws InterruptedException {
        //send event to lift console to open door
        Thread.sleep(10000);
        System.out.println("Opening door");
    }

    private void closeDoor() throws InterruptedException {

        System.out.println("closing door");
        //send event to lift console to close door
        Thread.sleep(10000);
    }

    private void setDirections()
    {
        if(currentDirections == Directions.NONE) {

            if(upDestinationRequest.size() > 0 && downDestinationRequest.size() > 0)
            {
                if(Math.abs(currentFloor.intValue() - upDestinationRequest.first().intValue()) < Math.abs(currentFloor.intValue() - downDestinationRequest.first().intValue()))
                {
                    currentDirections = Directions.UP;
                } else {
                    currentDirections = Directions.DOWN;
                }
            } else if(upDestinationRequest.size() > 0) {
                currentDirections = Directions.UP;
            } else if(downDestinationRequest.size() > 0) {
                currentDirections = Directions.DOWN;
            }
        }

    }

    public synchronized void addRequestFromOuterControl(Request request) {

        if (!isValidRequest(request)) {
            return;
        }

        if (request.getRequestedFloor() > currentFloor.intValue()) {
            upDestinationRequest.add(request.getRequestedFloor());
        } else {
            downDestinationRequest.add(request.getRequestedFloor());
        }

        if (currentState == States.WAITING) {
            currentState = States.MOVING;
            notify();
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
