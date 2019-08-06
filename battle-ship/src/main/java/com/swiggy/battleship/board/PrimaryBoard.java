package com.swiggy.battleship.board;

import com.swiggy.battleship.constants.BoxColour;
import com.swiggy.battleship.constants.ErrorCodes;
import com.swiggy.battleship.constants.GameConstants;
import com.swiggy.battleship.exception.BattleShipException;
import com.swiggy.battleship.pojo.Point;
import com.swiggy.battleship.ships.AbstractShip;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class PrimaryBoard extends AbstractBoard {

    private int MAX_SHIP_COUNT;
    private int CURRENT_SHIP_COUNT;

    public PrimaryBoard(int size, int shipCapcity) {
        super(size);
        this.MAX_SHIP_COUNT=shipCapcity;
        this.CURRENT_SHIP_COUNT=0;

    }

    public void drawBoard(List<AbstractShip> ships) throws BattleShipException {
        for (AbstractShip ship: ships) {
            placeShipOnBoard(ship);
        }
    }

    public void placeShipOnBoard(AbstractShip ship) throws BattleShipException {

        boolean isValidToPlaceHorizontally = false;
        boolean isValidToPlaceVertically = false;
        Point point = null;
        for (Box[] boxes : board) {
            for (Box box : boxes) {
                if(box.getShip().isPresent()) {
                    continue;
                }
                point = box.getPosition();
                if(isValidPositionToPlaceShip(ship.getSize(),point.getRowIndex(),point.getColIndex(),true)) {
                    isValidToPlaceHorizontally =true;
                    break;
                }
                if(isValidPositionToPlaceShip(ship.getSize(),point.getRowIndex(),point.getColIndex(),false)) {
                    isValidToPlaceVertically = true;
                    break;
                }
            }
            if(isValidToPlaceHorizontally || isValidToPlaceVertically) {
                break;
            }
        }

        if(isValidToPlaceHorizontally || isValidToPlaceVertically) {
            if(isValidToPlaceHorizontally) {
                placeShipHorizontally(ship,point.getRowIndex(), point.getColIndex());
            } else {
                placeShipVertically(ship,point.getRowIndex(),point.getColIndex());
            }
        } else {
            throw new BattleShipException(ErrorCodes.NO_SLOT_AVAILABLE_FOR_SHIP.getErrorMessage());
        }

    }
    private void placeShipHorizontally(final AbstractShip ship, final int x, final int y) {

        for (int col = y; col < y+ship.getSize(); col++) {
            Box box = board[x][col];
            box.setShip(Optional.of(ship));
        }

    }
    private void placeShipVertically(final AbstractShip ship, final int x, final int y) {
        for (int row=x; row < x + ship.getSize(); row++) {
            Box box = board[row][y];
            box.setShip(Optional.of(ship));
        }
    }
    private boolean isValidPositionToPlaceShip(final int shipLength, final int x,
                                               final int y, final boolean placeHorizontaly) {
        if(placeHorizontaly) {
            if(y+shipLength-1 >= board[0].length) {
                return false;
            }
            for (int col = y; col < y + shipLength; col++) {
                Box box = board[x][col];
                if(box.getShip().isPresent()) {
                    return false;
                }
            }
        } else {
            if(x + shipLength - 1 >= board.length) {
                return false;
            }
            for (int row = x; row < x + shipLength; row++) {
                Box box = board[row][y];
                if(box.getShip().isPresent()) {
                    return false;
                }
            }
        }
        return true;

    }

    public boolean isHitOnShip(Point attackPoint) {
        //validateAttackPoint(attackPoint);
        int x = attackPoint.getRowIndex();
        int y = attackPoint.getColIndex();
        Box box = board[x][y];
        return box.getShip().isPresent();
    }

    public void attack(Point attackPoint) throws BattleShipException {

        validateAttackPoint(attackPoint);

        int x = attackPoint.getRowIndex();
        int y = attackPoint.getColIndex();
        Box box = board[x][y];
        if(box.getShip().isPresent()) {
            AbstractShip ship = box.getShip().get();
            ship.attackOnShip();
            box.setBoxColour(BoxColour.RED);
        } else {
            box.setBoxColour(BoxColour.WHITE);
        }

    }

    private void validateAttackPoint(Point attackPoint) throws BattleShipException {
        int x = attackPoint.getRowIndex();
        int y = attackPoint.getColIndex();
        if((x < 0 && x >= Integer.valueOf(GameConstants.BOARD_SIZE)) ||
                (y < 0 && y>=Integer.valueOf(GameConstants.BOARD_SIZE))) {
            throw new BattleShipException(ErrorCodes.NOT_VALID_SHOT.getErrorMessage());
        }
        Box box = board[x][y];
        if(box.getBoxColour() != BoxColour.DEFAULT || box.getShip().isPresent() && box.getShip().get().getIsSunk()) {
            throw new BattleShipException(ErrorCodes.NOT_VALID_SHOT.getErrorMessage());
        }

    }

    public boolean hasAllShipSunk() {

        for (Box[] row : board) {
            for (Box box : row) {
                if(box.getShip().isPresent()) {
                    if(!box.getShip().get().getIsSunk()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
