public class Pawn extends ChessPiece {


    public Pawn() {
        super(ChessPieceTypes.PAWN);
    }

    public boolean canMove(Board board, Box startBox, Box endBox) {
        return false;
    }

    public void makeMove() {

    }
}
