package com.swiggy.battleship;

import com.swiggy.battleship.exception.BattleShipException;
import com.swiggy.battleship.game.BattleShipGame;
import com.swiggy.battleship.inputs.ConsoleReader;

public class Main {
    public static void main(String[] args) throws BattleShipException {

        BattleShipGame battleShipGame = new BattleShipGame("Game1","1232a", new ConsoleReader());
        battleShipGame.intializeGame();
        battleShipGame.run();

    }
}
