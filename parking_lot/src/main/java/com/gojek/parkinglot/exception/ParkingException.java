package com.gojek.parkinglot.exception;

public class ParkingException extends Exception {

    public ParkingException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public ParkingException(final String message) {
        super(message);
    }
}
