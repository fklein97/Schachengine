package boardTests;

import Board.BoardHistory;
import Board.Position;
import Board.Pawn;
import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

    private Position position1 = new Position(1,2,new Pawn(true));
    private Position position2 = new Position(1,2,new Pawn(true));
    private Position position3 = new Position(1,2,new Pawn(false));

    @Test
    public void equals() throws Exception{

        Assert.assertEquals(true, position1.equals(position2));
        Assert.assertEquals(false, position1.equals(position3));
    }
}
