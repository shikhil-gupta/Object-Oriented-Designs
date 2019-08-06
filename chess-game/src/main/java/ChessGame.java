import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChessGame {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;
    List<Move> listOfMoves;



    public void move(int userid, int startPosition, int endPosition) throws Exception {


        validatePlayerTurn(userid);
        Box startbox = board.getPositionToBoxMap().get(startPosition);
        Box endBox = board.getPositionToBoxMap().get(endPosition);
        //validate endbox does not have already have any other pieces

        //validate startbox piece can move to new position

        Move move = new Move();
        move.setStartPosition(startPosition);
        move.setEndPosition(endPosition);
        move.setPiece(startbox.getPiece().get());

        move.validateMove();

        move.moveToNewPosition();
        listOfMoves.add(move);
    }

    private void validatePlayerTurn(int userid) throws Exception {
        if(!currentPlayer.getUserid().equals(userid)) {
            throw new Exception("Invalid player turn");
        }
    }

}

