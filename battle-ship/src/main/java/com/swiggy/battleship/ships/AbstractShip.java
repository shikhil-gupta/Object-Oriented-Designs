package com.swiggy.battleship.ships;

import lombok.Getter;

@Getter
public abstract class AbstractShip {

    private String name;

    private int size;

    private int lifeLeft;

    private Boolean isSunk;

    AbstractShip(final String name, final int size) {
        this.name=name;
        this.size=size;
        this.lifeLeft=size;
        this.isSunk=Boolean.FALSE;
    }

    public void attackOnShip() {
        if(lifeLeft == 1) {
            lifeLeft = lifeLeft-1;
            isSunk = Boolean.TRUE;
        } else {
            lifeLeft--;
        }
    }

}
