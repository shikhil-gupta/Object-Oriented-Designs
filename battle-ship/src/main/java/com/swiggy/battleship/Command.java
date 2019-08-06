package com.swiggy.battleship;

import com.swiggy.battleship.constants.CommandConstants;
import com.swiggy.battleship.pojo.Point;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Command {

    @Setter
    private int playerId;
    private CommandConstants commandType;
    @Setter
    private Point position;

    public Command(final CommandConstants commandType) {
        this.commandType=commandType;
    }

}
