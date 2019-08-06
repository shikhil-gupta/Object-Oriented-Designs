import lombok.Getter;

import java.util.HashMap;

@Getter
public class Board {

    HashMap<Integer, Box> positionToBoxMap;

    public Board() {
        positionToBoxMap = new HashMap<Integer, Box>();
    }

}
