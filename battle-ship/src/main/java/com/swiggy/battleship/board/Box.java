package com.swiggy.battleship.board;

import com.swiggy.battleship.constants.BoxColour;
import com.swiggy.battleship.pojo.Point;
import com.swiggy.battleship.ships.AbstractShip;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
public class Box {

    private Point position;

    @Setter
    private Optional<AbstractShip> ship;

    @Setter
    private BoxColour boxColour;

    public Box(final Point position) {
        this.position=position;
        this.boxColour = BoxColour.DEFAULT;
        this.ship = Optional.empty();
    }



}
