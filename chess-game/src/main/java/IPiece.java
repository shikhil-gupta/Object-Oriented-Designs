public interface IPiece {

    boolean canMove(Board board, Box startBox, Box endBox);
    void makeMove();


}
