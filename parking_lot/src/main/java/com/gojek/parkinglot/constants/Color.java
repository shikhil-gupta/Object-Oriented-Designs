package com.gojek.parkinglot.constants;

import com.gojek.parkinglot.exception.ParkingException;

public enum Color {
    BLACK("Black"), BLUE("Blue"), WHITE("White"), RED("Red");

    private String color;

    Color(String color) {
        this.color = color;
    }

    public static Color getColorByValue(final String colorString) throws ParkingException {

        for (Color color : Color.values()) {
            if (colorString.equalsIgnoreCase(color.getColor())) {
                return color;
            }
        }
        throw new ParkingException("Not Valid Color");
    }

    public String getColor() {
        return color;
    }

}
