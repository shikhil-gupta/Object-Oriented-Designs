package com.elevator.design.handler.outer;


import com.elevator.design.Request;
import com.elevator.design.commands.ICommand;
import com.elevator.design.commands.OuterDownCommand;
import com.elevator.design.commands.OuterUPCommand;
import com.elevator.design.constants.Directions;
import com.elevator.design.service.IElevatorService;

public class OuterRequestHandlerImpl implements IOuterRequestHandler {


    private IElevatorService elevatorService;

    public void requestForUPDirection(int floorNo, int elevatorId) {
        ICommand command = new OuterUPCommand(elevatorService);
        Request request = new Request();
        request.setElevatorId(elevatorId);
        request.setRequestedFloor(floorNo);
        request.setRequestedDirections(Directions.UP);
        command.execute(request);

    }

    public void requestForDownDirections(int floorNo, int elevatorId) {

        ICommand command = new OuterDownCommand(elevatorService);
        Request request = new Request();
        request.setElevatorId(elevatorId);
        request.setRequestedFloor(floorNo);
        request.setRequestedDirections(Directions.DOWN);
        command.execute(request);

    }
}
