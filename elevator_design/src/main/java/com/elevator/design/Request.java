package com.elevator.design;

import com.elevator.design.constants.Directions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {

    private int elevatorId;

    private int requestedFloor;

    private Directions requestedDirections;

}
