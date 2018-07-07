package MoveGenerator;

import Board.ChessBoard;
import Board.Position;

import java.util.ArrayList;

/**
 * Created by FKPro on 07.07.2018.
 */
public class DangerChecker {
    /**
     * Calculates all Danger Positions for a King of a given color
     * @param chessBoard
     * @param forWhite
     * @return
     */
    public static ArrayList<Position> getDangerPositions(ChessBoard chessBoard, boolean forWhite){
       ArrayList<Position> dangers = new ArrayList<>();

       if(forWhite){
           ArrayList<Position> black_positions = chessBoard.getPositions();
           for (Position p:black_positions) {
               if(p.getPiece().isWhite()==true){
                   black_positions.remove(p);
               }
           }
           for (Position p:black_positions) {
               dangers.addAll(MoveGenerator.getMoveSet(p,chessBoard));
           }
       }
       else{
           ArrayList<Position> white_positions = chessBoard.getPositions();
           for (Position p:white_positions) {
               if(p.getPiece().isWhite()==false){
                   white_positions.remove(p);
               }
           }
           for (Position p:white_positions) {
               dangers.addAll(MoveGenerator.getMoveSet(p,chessBoard));
           }
       }

       return dangers;
    }
}
