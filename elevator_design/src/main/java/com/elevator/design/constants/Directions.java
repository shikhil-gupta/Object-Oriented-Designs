package com.elevator.design.constants;

public enum Directions {

    UP(0), DOWN(1), DEFAULT(-1);

    private int value;

    Directions(int value) {
        this.value=value;
    }

}
