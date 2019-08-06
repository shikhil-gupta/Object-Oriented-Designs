import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Move {

    private Integer startPosition;
    private Integer endPosition;
    private Integer playerid;
    private IPiece piece;
    private Board board;

    public void validateMove() {

    }

    public void moveToNewPosition() {

    }

}
