package com.elevator.design.commands;

import com.elevator.design.Request;
import com.elevator.design.service.IElevatorService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OuterUPCommand implements ICommand {

    private IElevatorService iElevatorService;

    public void execute(Request request) {
        iElevatorService.placeRequest(request);
    }
}
