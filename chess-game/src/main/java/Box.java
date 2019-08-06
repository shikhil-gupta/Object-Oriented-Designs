import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Box {

    private Integer position;
    private BoxType boxtype;
    private Optional<IPiece> piece;

    public Box(BoxType boxType) {
        this.boxtype = boxType;
    }

}
