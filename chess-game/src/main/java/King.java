public class King extends ChessPiece {


    public King(ChessPieceTypes chessPieceTypes) {
        super(chessPieceTypes);
    }

    public boolean canMove(Board board, Box startBox, Box endBox) {
        return false;
    }

    public void makeMove() {

    }
}
