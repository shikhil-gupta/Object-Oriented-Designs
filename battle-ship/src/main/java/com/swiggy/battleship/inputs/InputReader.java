package com.swiggy.battleship.inputs;

import com.swiggy.battleship.Command;

public interface InputReader {

    Command takePlayerInput(final int playerId);

    void sendStatus(final int playerId, final String status);

}
