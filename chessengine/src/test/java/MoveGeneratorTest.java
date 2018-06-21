import org.junit.*;
<<<<<<< HEAD
import java.util.ArrayList;
import Board.*;
=======
//import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import Board.*;
import MoveGenerator.*;
>>>>>>> master

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
    private void setUp(){
        positions.add(new Position(4,2, whitepawn));
        positions.add(new Position(8,1, whiterook));

        movedblackpawn.move();
        positions.add(new Position(7,7, movedblackpawn));
        positions.add(new Position(4,5, blackrook));
    }

    @Test
    void WhitePawnMoveSetTest() {
        setUp();
        ArrayList<Position> expectedMoveset = new ArrayList<Position>();
        expectedMoveset.add(new Position(4,3,whitepawn));
        expectedMoveset.add(new Position(4,4,whitepawn));

        Assert.assertEquals(expectedMoveset, MoveGenerator.getMoveSet(new Position(4,2,whitepawn)));
    }
}
