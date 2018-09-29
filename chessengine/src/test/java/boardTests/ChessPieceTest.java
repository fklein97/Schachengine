package boardTests;

import Board.*;
import org.junit.Assert;
import org.junit.Test;

public class ChessPieceTest {

    private Pawn pawn1 = new Pawn(true);
    private Pawn pawn2 = new Pawn(true);
    private Pawn pawn3 = new Pawn(false);

    @Test
    public void equals(){

        Assert.assertEquals(true, pawn1.equals(pawn2));
        Assert.assertEquals(false, pawn1.equals(pawn3));

    }

    @Test
    public void string(){
        Assert.assertEquals("white pawn", pawn1.toString());
        Assert.assertEquals("black pawn", pawn3.toString());

    }
}
