public abstract class ChessPiece implements IPiece {

    private ChessPieceTypes chessPieceTypes;

    public ChessPiece(ChessPieceTypes chessPieceTypes) {
        this.chessPieceTypes=chessPieceTypes;
    }
}
