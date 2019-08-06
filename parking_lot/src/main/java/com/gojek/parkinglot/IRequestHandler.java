package com.gojek.parkinglot;

import com.gojek.parkinglot.exception.ParkingException;

/**
 * This interface is for request handler. This can be implemented by different types handler,
 * This provide execute function which has to be implemented by child class.
 */
public interface IRequestHandler {
    void execute(final String command) throws ParkingException;
}
