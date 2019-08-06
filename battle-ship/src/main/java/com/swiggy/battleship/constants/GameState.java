package com.swiggy.battleship.constants;

public enum GameState {
    WAITING(0),
    RUNNING(1),
    GAME_END(2);

    private int gameStateValue;

    GameState(final int gameStateValue) {
        this.gameStateValue=gameStateValue;
    }


}
