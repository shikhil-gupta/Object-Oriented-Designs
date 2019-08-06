package com.swiggy.battleship.ships;

import com.swiggy.battleship.constants.GameConstants;

public class CruiserShip extends AbstractShip {

    public CruiserShip() {
        super(GameConstants.CRUISER_SHIP_NAME, Integer.valueOf(GameConstants.CRUISER_SHIP_SIZE));
    }
}
