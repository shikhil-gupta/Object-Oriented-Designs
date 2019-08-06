package com.elevator.design.commands;

import com.elevator.design.Request;

public interface ICommand {
    void execute(Request request);
}
