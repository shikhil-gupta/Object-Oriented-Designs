package com.swiggy.battleship.ships;


import com.swiggy.battleship.constants.GameConstants;

public class BattleShip extends AbstractShip {

    public BattleShip() {
        super(GameConstants.BATTLE_SHIP_NAME, Integer.valueOf(GameConstants.BATTLE_SHIP_SIZE));
    }

}
