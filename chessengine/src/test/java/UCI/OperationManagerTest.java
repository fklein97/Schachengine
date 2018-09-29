package UCI;

import Board.ChessBoard;
import Board.ChessPiece;
import Board.Position;
import org.junit.Assert;
import org.junit.Test;


class OperationManagerTest {
    OperationManager manager = new OperationManager();

    @Test
    void handleFEN() {
        ChessBoard board = new ChessBoard();
        manager.handleFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        Assert.assertEquals(board.getPositions().size(),manager.getBoard().getPositions().size());
    }

    @Test
    void posToString() {

        Assert.assertEquals("a1",OperationManager.posToString(new Position(1,1,new ChessPiece(true))));
    }
}