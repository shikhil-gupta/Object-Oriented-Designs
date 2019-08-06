package com.swiggy.battleship.board;

import com.swiggy.battleship.constants.BoxColour;
import com.swiggy.battleship.constants.ErrorCodes;
import com.swiggy.battleship.exception.BattleShipException;
import com.swiggy.battleship.pojo.Point;
import lombok.Getter;

@Getter
public class TrackingBoard extends AbstractBoard{

    public TrackingBoard(int size) {
        super(size);
    }

    public void trackRecord(final Point position, final BoxColour boxColour) throws BattleShipException {
        if(isValidPosition(position)) {
            board[position.getRowIndex()][position.getColIndex()].setBoxColour(boxColour);
            return;
        }
        throw new BattleShipException(ErrorCodes.NOT_VALID_SHOT.getErrorMessage());
    }

    private boolean isValidPosition(Point position)  {
        int x = position.getRowIndex();
        int y = position.getColIndex();
        if(x < 0 || x >= size || y<0 || y>=size) {
            return false;
        }

        Box box = board[position.getRowIndex()][position.getColIndex()];
        return box.getBoxColour() == BoxColour.DEFAULT ? true :false;

    }

    public String getStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Box[] boxArray: board) {
            for (Box box : boxArray) {
                stringBuilder.append(box.getBoxColour().getColourValue());
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


}
