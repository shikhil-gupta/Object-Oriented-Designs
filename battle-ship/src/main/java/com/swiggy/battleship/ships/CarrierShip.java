package com.swiggy.battleship.ships;

import com.swiggy.battleship.constants.GameConstants;

public class CarrierShip extends AbstractShip {

    public CarrierShip() {
        super(GameConstants.CARRIER_SHIP_NAME, Integer.valueOf(GameConstants.CARRIER_SHIP_SIZE));
    }

}
