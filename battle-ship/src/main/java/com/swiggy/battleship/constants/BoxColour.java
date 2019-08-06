package com.swiggy.battleship.constants;

import lombok.Getter;

@Getter
public enum BoxColour {

    RED(1),WHITE(2),DEFAULT(0);

    private int colourValue;

    BoxColour(int colourValue) {
        this.colourValue=colourValue;
    }

}
