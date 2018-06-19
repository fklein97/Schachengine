package boardTests;

import Board.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class ChessBoardTest {

    private ChessBoard chessBoard = new ChessBoard();
    private Position positionFrom = new Position(1, 2, new Pawn(true));
    private Position positionTo = new Position(1, 1, new Rook(true));
    private ArrayList<Position> positions = new ArrayList<Position>();

    private void setUp() {
        positions.add(new Position(1, 1, new Pawn(true)));
        positions.add(new Position(2, 2, new Pawn(true)));
        positions.add(new Position(3, 2, new Pawn(true)));
        positions.add(new Position(4, 2, new Pawn(true)));
        positions.add(new Position(5, 2, new Pawn(true)));
        positions.add(new Position(6, 2, new Pawn(true)));
        positions.add(new Position(7, 2, new Pawn(true)));
        positions.add(new Position(8, 2, new Pawn(true)));
        positions.add(new Position(2, 1, new Knight(true)));
        positions.add(new Position(3, 1, new Bishop(true)));
        positions.add(new Position(4, 1, new King(true)));
        positions.add(new Position(5, 1, new Queen(true)));
        positions.add(new Position(6, 1, new Bishop(true)));
        positions.add(new Position(7, 1, new Knight(true)));
        positions.add(new Position(8, 1, new Rook(true)));
        positions.add(new Position(1, 7, new Pawn(false)));
        positions.add(new Position(2, 7, new Pawn(false)));
        positions.add(new Position(3, 7, new Pawn(false)));
        positions.add(new Position(4, 7, new Pawn(false)));
        positions.add(new Position(5, 7, new Pawn(false)));
        positions.add(new Position(6, 7, new Pawn(false)));
        positions.add(new Position(7, 7, new Pawn(false)));
        positions.add(new Position(8, 7, new Pawn(false)));
        positions.add(new Position(1, 8, new Rook(false)));
        positions.add(new Position(2, 8, new Knight(false)));
        positions.add(new Position(3, 8, new Bishop(false)));
        positions.add(new Position(4, 8, new King(false)));
        positions.add(new Position(5, 8, new Queen(false)));
        positions.add(new Position(6, 8, new Bishop(false)));
        positions.add(new Position(7, 8, new Knight(false)));
        positions.add(new Position(8, 8, new Rook(false)));
    }



    @Test
    public void positionIsTaken() throws Exception {

        Assert.assertEquals(chessBoard.positionIsTaken(1,2), true);
    }

    @Test
    public void getPositions() throws Exception {
        Assert.assertEquals(chessBoard.getPositions().get(0).equals(new Position(1,2,new Pawn(true))),true );
    }

    @Test
    public void setPositions() throws Exception {

    }

    @Test
    public void newGame() throws Exception {
    }

    @Test
    public void newGame1() throws Exception {
    }

    @Test
    public void move() throws Exception {
        setUp();
        chessBoard.move(positionFrom, positionTo);
        Assert.assertEquals(positions.get(0).getX(), chessBoard.getPositions().get(0).getX());
        Assert.assertEquals(positions.get(0).getY(), chessBoard.getPositions().get(0).getY());
        Assert.assertEquals(positions.get(0).getPiece().getClass(), chessBoard.getPositions().get(0).getPiece().getClass());

        Assert.assertEquals(positions.get(8).getX(), chessBoard.getPositions().get(8).getX());
        Assert.assertEquals(positions.get(8).getY(), chessBoard.getPositions().get(8).getY());
        Assert.assertEquals(positions.get(8).getPiece().getClass(), chessBoard.getPositions().get(8).getPiece().getClass());

    }

    @Test
    public void promote(){

        chessBoard.promote(new Position(2,2, new Pawn (true)), new Position(2,8, new Pawn(true)), new Queen(true));
        Assert.assertEquals(chessBoard.getPositions().get(1).equals(new Position(2,8, new Queen(true))), true );

    }

}