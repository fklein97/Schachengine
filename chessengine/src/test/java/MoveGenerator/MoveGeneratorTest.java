package MoveGenerator;

import org.junit.*;
import java.util.ArrayList;
import Board.*;
//import org.junit.jupiter.api.Test;
import MoveGenerator.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by FKPro on 19.06.2018.
 */
public class MoveGeneratorTest {

    private ArrayList<Position> positions   = new ArrayList<Position>();
    Pawn movedblackpawn = new Pawn(false);
    Pawn whitepawn = new Pawn(true);
    Rook whiterook = new Rook(true);
    Rook blackrook = new Rook(false);
    Knight whiteknight = new Knight(true);
    Bishop whitebishop = new Bishop(true);
    Queen whitequeen = new Queen(true);
    private void setUp(){
        positions.add(new Position(4,2, whitepawn));
        positions.add(new Position(8,1, whiterook));
        positions.add(new Position(2,2, whiteknight));
        positions.add(new Position(3, 3, whitebishop));
        positions.add(new Position(1,2,whitequeen));

        movedblackpawn.move();
        positions.add(new Position(7,7, movedblackpawn));
        positions.add(new Position(4,5, blackrook));
    }

    @Test
    public void WhitePawnMoveSetTest() {
        setUp();
        ArrayList<Position> expectedMoveset = new ArrayList<Position>();
        ArrayList<Position> calculatedMoveset = MoveGenerator.getMoveSet(new Position(4,2,whitepawn), new ChessBoard(positions));
        expectedMoveset.add(new Position(4,3,whitepawn));
        expectedMoveset.add(new Position(4,4,whitepawn));

       //Assert.assertEquals(expectedMoveset, MoveGenerator.getMoveSet(new Position(4,2,whitepawn)));

        Assert.assertEquals(expectedMoveset.get(0).equals(calculatedMoveset.get(0)) , true);
        Assert.assertEquals(expectedMoveset.get(1).equals(calculatedMoveset.get(1)) , true);
    }


    @Test
    public void MovedBlackPawnMoveSetTest(){
        setUp();
        ArrayList<Position> expectedMoveset = new ArrayList<Position>();
        ArrayList<Position> calculatedMoveset = MoveGenerator.getMoveSet(new Position(7,7,movedblackpawn), new ChessBoard(positions));
        expectedMoveset.add(new Position(7,6, movedblackpawn));

        Assert.assertEquals(expectedMoveset.get(0).equals(calculatedMoveset.get(0)), true);
    }

    @Test
    public void WhiteRookMoveSet(){
        setUp();
        ArrayList<Position> expectedMoveset = new ArrayList<Position>();
        ArrayList<Position> calculatedMoveset = MoveGenerator.getMoveSet(new Position(8,1,whiterook), new ChessBoard(positions));

        for(int i = 7; i > 0; i--){
            expectedMoveset.add(new Position(i,1, whiterook));
        }
        for(int i = 2; i < 9; i++){
            expectedMoveset.add(new Position(8,  i , whiterook));
        }


        for(int i = 0; i < calculatedMoveset.size(); i++){
            Assert.assertEquals(expectedMoveset.get(i).equals(calculatedMoveset.get(i)), true);
        }

    }

    @Test
    public void  WhiteKnightMoveSet(){
        setUp();
        ArrayList<Position> expectedMoveset = new ArrayList<Position>();
        ArrayList<Position> calculatedMoveset = MoveGenerator.getMoveSet(new Position(2,2,whiteknight), new ChessBoard(positions));

        expectedMoveset.add(new Position(4, 3, whiteknight));   //3
        expectedMoveset.add(new Position(4, 1, whiteknight));   //4


        expectedMoveset.add(new Position(3, 4, whiteknight));   //2
        expectedMoveset.add(new Position(1, 4, whiteknight));   //1



        for(int i = 0; i < calculatedMoveset.size(); i++){
            Assert.assertEquals(expectedMoveset.get(i).equals(calculatedMoveset.get(i)), true);
        }

    }


    @Test
    public void WhiteBishopMoveSet(){
        setUp();

        ArrayList<Position> expectedMoveset = new ArrayList<Position>();
        ArrayList<Position> calculatedMoveset = MoveGenerator.getMoveSet(new Position(3,3,whitebishop), new ChessBoard(positions));


        expectedMoveset.add(new Position(4,4, whitebishop));
        expectedMoveset.add(new Position(5,5, whitebishop));
        expectedMoveset.add(new Position(6,6, whitebishop));
        expectedMoveset.add(new Position(7,7, whitebishop));
        expectedMoveset.add(new Position(2,4, whitebishop));
        expectedMoveset.add(new Position(1,5, whitebishop));


        for(int i = 0; i < calculatedMoveset.size(); i++){
            Assert.assertEquals(expectedMoveset.get(i).equals(calculatedMoveset.get(i)), true);
        }

    }

    @Test
    public void WhiteQueenMoveSet(){
        setUp();

        ArrayList<Position> expectedMoveset = new ArrayList<Position>();
        ArrayList<Position> calculatedMoveset = MoveGenerator.getMoveSet(new Position(1,2,whitequeen), new ChessBoard(positions));

        for(int i = 3; i < 9; i++){
            expectedMoveset.add(new Position(1,i, whitequeen));
        }
        expectedMoveset.add(new Position(1,1, whitequeen));
        for(int i = 2; i < 5; i++){
            expectedMoveset.add(new Position(i,i+1, whitequeen));
        }
        expectedMoveset.add(new Position(2,1, whitequeen));

        for(int i = 0; i < calculatedMoveset.size(); i++){
            Assert.assertEquals(expectedMoveset.get(i).equals(calculatedMoveset.get(i)), true);
        }
    }

    


}
