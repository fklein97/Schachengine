package BoardRaterTest;

import Board.*;
import Rating.BoardRater;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BoardRaterTest {

    private ChessBoard chessBoard1 = new ChessBoard();
    private ChessBoard chessBoard2 = new ChessBoard();
    private ArrayList<Position> positions1 = new ArrayList<Position>();
    private ArrayList<Position> positions2 = new ArrayList<Position>();

    private void setUp() {
        positions1.add(new Position(1, 2, new Pawn(true)));
        positions1.add(new Position(2, 2, new Pawn(true)));
        positions1.add(new Position(3, 2, new Pawn(true)));
        positions1.add(new Position(4, 2, new Pawn(true)));
        positions1.add(new Position(5, 2, new Pawn(true)));
        positions1.add(new Position(6, 2, new Pawn(true)));
        positions1.add(new Position(7, 2, new Pawn(true)));
        positions1.add(new Position(8, 2, new Pawn(true)));
        positions1.add(new Position(1, 1, new Rook(true)));
        positions1.add(new Position(2, 1, new Knight(true)));
        positions1.add(new Position(3, 1, new Bishop(true)));
        positions1.add(new Position(4, 1, new King(true)));
        positions1.add(new Position(5, 1, new Queen(true)));
        positions1.add(new Position(6, 1, new Bishop(true)));
        positions1.add(new Position(7, 1, new Knight(true)));
        positions1.add(new Position(8, 1, new Rook(true)));
        positions1.add(new Position(1, 7, new Pawn(false)));
        positions1.add(new Position(2, 7, new Pawn(false)));
        positions1.add(new Position(3, 7, new Pawn(false)));
        positions1.add(new Position(4, 7, new Pawn(false)));
        positions1.add(new Position(5, 7, new Pawn(false)));
        positions1.add(new Position(6, 7, new Pawn(false)));
        positions1.add(new Position(7, 7, new Pawn(false)));
        positions1.add(new Position(8, 7, new Pawn(false)));
        positions1.add(new Position(1, 8, new Rook(false)));
        positions1.add(new Position(2, 8, new Knight(false)));
        positions1.add(new Position(3, 8, new Bishop(false)));
        positions1.add(new Position(4, 8, new King(false)));
        positions1.add(new Position(5, 8, new Queen(false)));
        positions1.add(new Position(6, 8, new Bishop(false)));
        positions1.add(new Position(7, 8, new Knight(false)));
        positions1.add(new Position(8, 8, new Rook(false)));

        positions2.add(new Position(1, 1, new King(false)));
        positions2.add(new Position(8, 8, new King(true)));



        positions2.add(new Position(1, 2, new Pawn(true)));
        //positions2.add(new Position(1, 3, new Pawn(true)));
        positions2.add(new Position(1, 7, new Pawn(false)));

    }



    @Test
    public void getBoardRatingTest() throws Exception {
        setUp();
        chessBoard1.setPositions(positions1);
        Assert.assertEquals(BoardRater.getBoardRating(chessBoard1), 0);
    }

    @Test
    public void getBoardRatingTest2() throws Exception{
        setUp();
        chessBoard2.setPositions(positions2);
        Assert.assertEquals(BoardRater.getBoardRating(chessBoard2), 0);
    }

}
