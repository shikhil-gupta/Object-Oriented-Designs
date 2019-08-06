package com.swiggy.battleship.game;

import com.swiggy.battleship.Command;
import com.swiggy.battleship.board.PrimaryBoard;
import com.swiggy.battleship.board.TrackingBoard;
import com.swiggy.battleship.constants.BoxColour;
import com.swiggy.battleship.constants.CommandConstants;
import com.swiggy.battleship.constants.GameConstants;
import com.swiggy.battleship.constants.GameState;
import com.swiggy.battleship.exception.BattleShipException;
import com.swiggy.battleship.inputs.InputReader;
import com.swiggy.battleship.pojo.Point;
import com.swiggy.battleship.ships.*;
import com.swiggy.battleship.users.Player;

public class BattleShipGame implements Runnable {

    private String gameId;
    private String gameName;
    private Player player1;
    private Player player2;
    private PrimaryBoard player1PrimaryBoard;
    private PrimaryBoard player2PrimaryBoard;
    private TrackingBoard player1TrackingBoard;
    private TrackingBoard player2TrackingBoard;
    private Player currentPlayer;
    private GameState currentGameState;
    private InputReader inputReader;

    public BattleShipGame(final String gameName, final String gameId, final InputReader inputReader) {
        this.gameName = gameName;
        this.gameId = gameId;
        this.inputReader = inputReader;
    }

    public void intializeGame() throws BattleShipException {
        player1 = new Player("Shikhil", String.valueOf(1));
        player2 = new Player("Nikhil", String.valueOf(2));
        player1PrimaryBoard = new PrimaryBoard(Integer.valueOf(GameConstants.BOARD_SIZE), Integer.valueOf(GameConstants.TOTAL_SHIP));
        player2PrimaryBoard = new PrimaryBoard(Integer.valueOf(GameConstants.BOARD_SIZE), Integer.valueOf(GameConstants.TOTAL_SHIP));

        player1PrimaryBoard.placeShipOnBoard(new BattleShip());
        player2PrimaryBoard.placeShipOnBoard(new BattleShip());

        player1PrimaryBoard.placeShipOnBoard(new CarrierShip());
        player2PrimaryBoard.placeShipOnBoard(new CarrierShip());

        player1PrimaryBoard.placeShipOnBoard(new CruiserShip());
        player2PrimaryBoard.placeShipOnBoard(new CruiserShip());

        player1PrimaryBoard.placeShipOnBoard(new DestroyerShip());
        player2PrimaryBoard.placeShipOnBoard(new DestroyerShip());

        player1PrimaryBoard.placeShipOnBoard(new SubmarineShip());
        player2PrimaryBoard.placeShipOnBoard(new SubmarineShip());

        player1TrackingBoard = new TrackingBoard(Integer.valueOf(GameConstants.BOARD_SIZE));
        player2TrackingBoard = new TrackingBoard(Integer.valueOf(GameConstants.BOARD_SIZE));
        currentGameState = GameState.WAITING;
        currentPlayer = player1;

    }

    @Override
    public void run() {

        while (true) {
            try {
                Command inputCommnand = null;
                if (currentGameState == GameState.WAITING) {
                    inputCommnand = inputReader.takePlayerInput(Integer.valueOf(currentPlayer.getPlayerId()));
                    if(inputCommnand.getCommandType() == CommandConstants.DRAW_BOARD) {
                        drawBoard();
                    } else if(inputCommnand.getCommandType() == CommandConstants.FIRE) {
                        currentGameState = GameState.RUNNING;
                    }
                }
                if (currentGameState == GameState.RUNNING) {
                    Point point = inputCommnand.getPosition();
                    move(point);
                    updateTrackRecord(point);
                    //drawBoard();
                    hasCurrentPlayerWonGame();
                    changeCurrentPlayer();
                }
                if(currentGameState == GameState.GAME_END) {
                    break;
                } else {
                    currentGameState = GameState.WAITING;
                }

            } catch (BattleShipException ex) {
                System.err.println(ex.getMessage());
                currentGameState = GameState.WAITING;
            }
        }

    }

    private void move(final Point point) throws BattleShipException {
        if(currentPlayer == player1) {
            player2PrimaryBoard.attack(point);
        } else {
            player1PrimaryBoard.attack(point);
        }
    }


    private void updateTrackRecord(final Point point) throws BattleShipException {
        BoxColour colour = null;
        if(currentPlayer == player1) {
            colour = player2PrimaryBoard.isHitOnShip(point) ? BoxColour.RED : BoxColour.WHITE;
            player1TrackingBoard.trackRecord(point, colour);
        } else {
            colour = player1PrimaryBoard.isHitOnShip(point) ? BoxColour.RED : BoxColour.WHITE;
            player2TrackingBoard.trackRecord(point, colour);
        }
        if(colour == BoxColour.RED) {
            inputReader.sendStatus(Integer.valueOf(currentPlayer.getPlayerId()), "Player "
                    + Integer.valueOf(currentPlayer.getPlayerId()) + " has successfully hit the enemy ship");
        } else {
            inputReader.sendStatus(Integer.valueOf(currentPlayer.getPlayerId()), "Player "
                    + Integer.valueOf(currentPlayer.getPlayerId()) + " has missed the shot");
        }
    }
    private void changeCurrentPlayer() {
        if(currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer=player1;
        }
    }

    private void drawBoard() {

        if(currentPlayer == player1) {
            String status = player1TrackingBoard.getStatus();
            inputReader.sendStatus(Integer.valueOf(currentPlayer.getPlayerId()), status);
        } else {
            String status = player2TrackingBoard.getStatus();
            inputReader.sendStatus(Integer.valueOf(currentPlayer.getPlayerId()), status);
        }
    }

    private boolean hasCurrentPlayerWonGame() {

        boolean result = true;
        if(currentPlayer == player1) {
            result = player2PrimaryBoard.hasAllShipSunk();
        } else {
            result = player2PrimaryBoard.hasAllShipSunk();
        }
        if(result) {
            inputReader.sendStatus(Integer.valueOf(currentPlayer.getPlayerId()),
                    "Player " +Integer.valueOf(currentPlayer.getPlayerId())+" has won the game");
            currentGameState = GameState.GAME_END;
        }
        return result;
    }



}
