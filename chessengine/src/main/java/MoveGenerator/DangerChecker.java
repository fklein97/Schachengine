package MoveGenerator;

import Board.*;

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
           for (Position p : black_positions) {
               if(!p.getPiece().isWhite()){
                   dangers.addAll(MoveGenerator.getBeatMoveSetwithoutDangerCheck(p,chessBoard,chessBoard));
               }
           }
       }
       else{
           ArrayList<Position> white_positions = chessBoard.getPositions();
           for (Position p : white_positions) {
               if(p.getPiece().isWhite()){
                   dangers.addAll(MoveGenerator.getBeatMoveSetwithoutDangerCheck(p,chessBoard,chessBoard));
               }
           }
       }

        return dangers;
    }

    public static ArrayList<Position> getDangerPositionsWithoutDuplicates(ChessBoard chessBoard, boolean forWhite){
        ArrayList<Position> dangers = getDangerPositions(chessBoard,forWhite);

        ArrayList<Position> duplicates = new ArrayList<>();
        for(int i = 0; i < dangers.size(); i++){
            for(int j = i+1; j <dangers.size(); j++){
                if(dangers.get(i).getX() == dangers.get(j).getX() && dangers.get(i).getY() == dangers.get(j).getY()){
                    duplicates.add(dangers.get(j));
                    break;
                }
            }
        }
        dangers.removeAll(duplicates);

        return dangers;
    }
}
