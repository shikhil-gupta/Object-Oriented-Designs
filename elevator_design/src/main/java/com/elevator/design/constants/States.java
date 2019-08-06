package com.elevator.design.constants;

public enum States {

    WAITING(0), MOVING(1), STOP(2), OPENDOOR(3), CLOSEDDOOR(4);

    private int stateValue;

    States(int stateValue) {
        this.stateValue=stateValue;
    }
}
