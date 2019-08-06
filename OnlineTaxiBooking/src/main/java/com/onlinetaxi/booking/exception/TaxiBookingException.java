package com.onlinetaxi.booking.exception;

public class TaxiBookingException extends Exception {

    public TaxiBookingException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TaxiBookingException(String message) {
        super(message);
    }
}
