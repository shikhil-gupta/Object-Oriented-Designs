package com.elevator.design.handler.inner;

import com.elevator.design.Request;
import com.elevator.design.handler.IRequestHandler;

public interface InnerRequestHandler extends IRequestHandler {


    void openDoor();

    void closeDoor();

    void placeNewRequest(Request request);

    void runFan();

    void callEmergency();

}
