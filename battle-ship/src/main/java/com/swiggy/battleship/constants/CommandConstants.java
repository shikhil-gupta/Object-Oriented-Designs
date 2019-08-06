package com.swiggy.battleship.constants;

import lombok.Getter;

@Getter
public enum CommandConstants {
    DRAW_BOARD("draw_board"), FIRE("fire");

    private String commandStr;

    CommandConstants(final String commandStr) {
        this.commandStr=commandStr;
    }

    public static CommandConstants getCommandConstantsByValue(final String commandStr) {
        for (CommandConstants commandConstant : CommandConstants.values()) {
            if(commandConstant.getCommandStr().equalsIgnoreCase(commandStr)) {
                return commandConstant;
            }
        }
        return null;
    }
}
