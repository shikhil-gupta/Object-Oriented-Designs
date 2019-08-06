package com.swiggy.battleship.pojo;

import lombok.Getter;

@Getter
public class Point {

    private int rowIndex;
    private int colIndex;

    public Point(int rowIndex, int colIndex) {
        this.rowIndex=rowIndex;
        this.colIndex=colIndex;
    }

}
