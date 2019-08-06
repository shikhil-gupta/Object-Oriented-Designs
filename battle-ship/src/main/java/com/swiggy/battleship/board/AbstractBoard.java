package com.swiggy.battleship.board;

import com.swiggy.battleship.pojo.Point;
import lombok.Getter;

@Getter
public abstract class AbstractBoard {
    protected Box[][] board;
    protected int size;

    public AbstractBoard(int size) {
        this.size=size;
        board = new Box[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                board[i][j] = new Box(new Point(i,j));
            }
        }
    }
}
