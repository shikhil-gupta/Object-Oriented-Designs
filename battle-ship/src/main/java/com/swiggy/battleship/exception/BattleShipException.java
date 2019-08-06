package com.swiggy.battleship.exception;

public class BattleShipException extends Exception {
    public BattleShipException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public BattleShipException(final String message) {
        super(message);
    }
}
