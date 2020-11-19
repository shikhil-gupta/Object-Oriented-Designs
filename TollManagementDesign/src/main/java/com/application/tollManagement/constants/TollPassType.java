package com.application.tollManagement.constants;

public enum TollPassType {

    ONE_TIME(0), RETURN_PASS(1), WEEKLY_PASS(2);

    private Integer tollPassType;

    TollPassType(final Integer tollPassType) {
        this.tollPassType = tollPassType;
    }
}
