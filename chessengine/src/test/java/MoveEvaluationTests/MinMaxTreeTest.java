package MoveEvaluationTests;
import java.util.ArrayList;
import Board.*;
import org.junit.Assert;
import org.junit.Test;
import MoveEvaluation.*;

public class MinMaxTreeTest {




    @Test
    public void generateTree() throws Exception {
        MinMaxTree tree = new MinMaxTree();
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(new Position(1,1, new King(true)));
        positions.add(new Position(8,8, new King(false)));
        positions.add(new Position(4,4, new Rook(true)));

        ChessBoard board = new ChessBoard(positions);
        Move move = tree.initialize(board, true);


        Position from = new Position(4,4, new Rook(true));
        Position to = new Position(4,7, new Rook(true));


        Assert.assertEquals(move.equals(new Move(from, to)), true);

    }



}
