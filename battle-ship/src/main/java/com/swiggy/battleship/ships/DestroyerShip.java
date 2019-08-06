package com.swiggy.battleship.ships;

import com.swiggy.battleship.constants.GameConstants;

public class DestroyerShip extends AbstractShip {

    public DestroyerShip() {
        super(GameConstants.DESTROYER_SHIP_NAME, Integer.valueOf(GameConstants.DESTROYER_SHIP_SIZE));
    }

}
