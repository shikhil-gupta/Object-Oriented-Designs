package com.elevator.design.handler.outer;


import com.elevator.design.handler.IRequestHandler;

public interface IOuterRequestHandler extends IRequestHandler {

    void requestForUPDirection(final int floorNo, final int elevatorId);

    void requestForDownDirections(final int floorNo, final int elevatorId);

}
