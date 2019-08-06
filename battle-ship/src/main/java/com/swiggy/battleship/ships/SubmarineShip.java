package com.swiggy.battleship.ships;

import com.swiggy.battleship.constants.GameConstants;

public class SubmarineShip extends AbstractShip {

    public SubmarineShip() {
        super(GameConstants.SUBMARINE_SHIP_NAME, Integer.valueOf(GameConstants.SUBMARINE_SHIP_SIZE));
    }

}
