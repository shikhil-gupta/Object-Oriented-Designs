package com.moengage.incidentalert.constants;

public enum EmployeeLevel {

    ZERO(0, "lowest level"), ONE(1," first level");


    private int levelCode;
    private String levelDescription;

    EmployeeLevel(int levelCode, String levelDescription) {
        this.levelCode = levelCode;
        this.levelDescription = levelDescription;
    }



}
