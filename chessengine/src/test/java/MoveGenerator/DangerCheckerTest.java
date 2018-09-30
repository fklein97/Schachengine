package MoveGenerator;

import org.junit.Assert;
import org.junit.Test;
import Board.*;

import java.util.ArrayList;

public class DangerCheckerTest {

    private boolean als;

    @Test
    public void KingDangerCheckTest(){
        Position rookPos = new Position(1,1, new Rook(true));
        ArrayList<Position> positions = new ArrayList<>();
        ArrayList<Position> boardDanger = new ArrayList<>();
        ArrayList<Position> boardDangerActual = new ArrayList<>();
        positions.add(new Position(8,8, new King(false)));
        positions.add(new Position(8,1, new King(true)));
        boolean test1 = false;
        boolean test2 = true;


        ChessBoard board = new ChessBoard(positions);

        boardDangerActual = DangerChecker.getDangerPositions(board, false);
        boardDanger.add(new Position(8,2, new King(true)));
        boardDanger.add(new Position(7,2, new King(true)));
        boardDanger.add(new Position(7,1, new King(true)));

        for (Position bd : boardDanger){
            for(Position bda : boardDangerActual){
                if(bd.equals(bda)){
                    test1 = true;
                }
            }
            if(!test1){
                test2 = false;
            }
            test1 = false;
        }

        Assert.assertEquals(test2, true);
    }


    @Test
    public void PawnDangerTest(){
        ArrayList<Position> positions = new ArrayList<>();
        ArrayList<Position> boardDanger = new ArrayList<>();
        ArrayList<Position> boardDangerActual = new ArrayList<>();
        positions.add(new Position(8,8, new King(false)));
        positions.add(new Position(8,1, new King(true)));
        positions.add((new Position(3,2,new Pawn(true))));
        boolean test1 = false;
        boolean test2 = true;


        ChessBoard board = new ChessBoard(positions);

        boardDangerActual = DangerChecker.getDangerPositions(board, false);
        boardDanger.add(new Position(8,2, new King(true)));
        boardDanger.add(new Position(7,2, new King(true)));
        boardDanger.add(new Position(7,1, new King(true)));
        boardDanger.add(new Position(2,3, new Pawn(true)));
        boardDanger.add(new Position(4,3, new Pawn(true)));

        for (Position bd : boardDanger){
            for(Position bda : boardDangerActual){
                if(bd.equals(bda)){
                    test1 = true;
                }
            }
            if(!test1){
                test2 = false;
            }
            test1 = false;
        }

        Assert.assertEquals(test2, true);
    }

    @Test
    public void KnghtDangerTest(){
        ArrayList<Position> positions = new ArrayList<>();
        ArrayList<Position> boardDanger = new ArrayList<>();
        ArrayList<Position> boardDangerActual = new ArrayList<>();
        positions.add(new Position(8,8, new King(false)));
        positions.add(new Position(8,1, new King(true)));
        positions.add(new Position(1,8, new Knight(true)));
        boolean test1 = false;
        boolean test2 = true;


        ChessBoard board = new ChessBoard(positions);

        boardDangerActual = DangerChecker.getDangerPositions(board, false);
        boardDanger.add(new Position(8,2, new King(true)));
        boardDanger.add(new Position(7,2, new King(true)));
        boardDanger.add(new Position(7,1, new King(true)));
        boardDanger.add(new Position(3,7, new Knight(true)));
        boardDanger.add(new Position(2,6, new Knight(true)));



        for (Position bd : boardDanger){
            for(Position bda : boardDangerActual){
                if(bd.equals(bda)){
                    test1 = true;
                }
            }
            if(!test1){
                test2 = false;
            }
            test1 = false;
        }

        Assert.assertEquals(test2, true);
    }

}
