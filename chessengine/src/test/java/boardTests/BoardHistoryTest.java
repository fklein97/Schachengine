package boardTests;

import Board.BoardHistory;
import Board.ChessBoard;
import Board.*;
import Rating.BoardRater;
import org.junit.Assert;
import org.junit.Test;

public class BoardHistoryTest {

    private ChessBoard chessBoard = new ChessBoard();


    @Test
    public void draw() throws Exception {


        BoardHistory.add(chessBoard);
        BoardHistory.add(chessBoard);
        BoardHistory.add(chessBoard);


        Assert.assertEquals(BoardHistory.draw(), true);
    }

    @Test
    public void clear() throws Exception{
        BoardHistory.clear();
        Assert.assertEquals(BoardHistory.draw(), false);
    }

    @Test
    public void rewind() throws Exception{
        BoardHistory.clear();
        BoardHistory.add(chessBoard);
        BoardHistory.add(chessBoard);
        BoardHistory.add(chessBoard);

        Assert.assertEquals(BoardHistory.draw(), true);
        BoardHistory.rewind();
        Assert.assertEquals(BoardHistory.draw(), false);

    }

    @Test
    public void add(){
        BoardHistory.clear();
        BoardHistory.add(chessBoard);
        BoardHistory.add(chessBoard);
        Assert.assertEquals(BoardHistory.draw(), false);

        BoardHistory.add(new Position(1,2,new Pawn(true)), new Position(1,3,new Pawn(true)));
        BoardHistory.add(new Position(1,3,new Pawn(true)), new Position(1,2,new Pawn(true)));


        Assert.assertEquals(BoardHistory.draw(), true);

    }

}
