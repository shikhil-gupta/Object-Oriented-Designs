package com.moengage.incidentalert.exception;

public class IncidentException extends Exception {

    public IncidentException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public IncidentException(final String message) {
        super(message);
    }
}
