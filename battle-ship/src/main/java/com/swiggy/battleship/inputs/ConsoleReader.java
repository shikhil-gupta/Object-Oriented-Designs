package com.swiggy.battleship.inputs;

import com.swiggy.battleship.Command;
import com.swiggy.battleship.constants.CommandConstants;
import com.swiggy.battleship.pojo.Point;

import java.util.Scanner;

public class ConsoleReader implements InputReader {

    @Override
    public Command takePlayerInput(final int playerId) {
        System.out.println("Player " + playerId + " turn");
        Scanner scanner = new Scanner(System.in);
        String inputCommand = scanner.nextLine();
        while (!validatePlayersTurn(playerId, inputCommand)) {
            System.err.println("Invalid Input, Please enter again");
            inputCommand = scanner.nextLine();
        }
        return parseCommand(playerId,inputCommand);
    }

    @Override
    public void sendStatus(final int playerId, final String status) {
        System.out.println(status);
    }

    private boolean validatePlayersTurn(final int playerId, final String inputCommand) {

        String[] parts = inputCommand.split(" ");
        if(parts.length == 1 && "exit".equalsIgnoreCase(parts[0])) {
            System.exit(0);
        }
        if (parts.length < 2) {
            return false;
        }
        if (isNumber(parts[0]) && Integer.valueOf(parts[0]) != playerId) {
            return false;
        }
        if (CommandConstants.FIRE.getCommandStr().equalsIgnoreCase(parts[1]) && parts.length == 3) {

            String position = parts[2];
            if (position.length() != 2 || Integer.valueOf(position.charAt(0)) < 65
                    && Integer.valueOf(position.charAt(0)) >= 75) {
                return false;
            }

            return true;
        }

        if (CommandConstants.DRAW_BOARD.getCommandStr().equalsIgnoreCase(parts[1])) {
            return true;
        }
        return false;
    }
    private Command parseCommand(final int playerId, final String inputCommand) {

        String[] parts = inputCommand.split(" ");

        CommandConstants commandConstant = CommandConstants.getCommandConstantsByValue(parts[1]);
        Command command = new Command(commandConstant);
        command.setPlayerId(playerId);
        if(commandConstant.getCommandStr().equalsIgnoreCase(CommandConstants.FIRE.getCommandStr())) {
            String position = parts[2];
            Point point = new Point(position.charAt(0)-65,position.charAt(1)-'0');
            command.setPosition(point);
        }
        return command;
    }

    private boolean isNumber(final String str) {
        try{
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
