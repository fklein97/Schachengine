package MoveEvaluationTests;
import java.util.ArrayList;
import Board.*;
import org.junit.Assert;
import org.junit.Test;
import MoveEvaluation.*;

public class MinMaxTreeTest {




    @Test
    public void generateTree() throws Exception {

        ChessBoard board = new ChessBoard();
        MinMaxTree tree = new MinMaxTree(board);
        tree.generateTree(4);                                  //holt letzter legaler move


        Move move = tree.getBestMove();
        //Move move2 = tree.getRoot().getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0).getMove();

        System.out.println("Piece: " + move.getPositionFrom().getPiece() + " from: " + move.getPositionFrom().getX() +""+ move.getPositionFrom().getY() +" to: "
                + move.getPositionTo().getPiece() +" "+move.getPositionTo().getX() + "" + move.getPositionTo().getY());
        //System.out.println("Piece: " + move2.getPositionFrom().getPiece() + " from: " + move2.getPositionFrom().getX() +""+ move2.getPositionFrom().getY() +" to: "
        //        + move2.getPositionTo().getPiece() +" "+move2.getPositionTo().getX() + "" + move2.getPositionTo().getY());

    }



}
