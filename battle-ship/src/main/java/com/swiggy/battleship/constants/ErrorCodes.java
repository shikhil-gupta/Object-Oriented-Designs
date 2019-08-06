package com.swiggy.battleship.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    NOT_VALID_SHOT(-1, "Sorry, Your shot on target is not valid"),
    NO_SLOT_AVAILABLE_FOR_SHIP(-1, "Sorry, Slots are not available on Board");

    private int errorValue;
    private String errorMessage;

    ErrorCodes(final int errorValue, final String errorMessage) {
        this.errorValue=errorValue;
        this.errorMessage=errorMessage;
    }
}
