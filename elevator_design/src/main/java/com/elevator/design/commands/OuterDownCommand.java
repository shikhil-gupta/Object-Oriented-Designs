package com.elevator.design.commands;

import com.elevator.design.Request;
import com.elevator.design.service.IElevatorService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OuterDownCommand implements ICommand {
    private IElevatorService iElevatorService;

    public void execute(Request request) {
        iElevatorService.placeRequest(request);
    }
}
