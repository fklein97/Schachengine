package MoveEvaluation;

import Board.*;
import MoveGenerator.*;
import Parameters.Parameters;
import Rating.BoardRater;

import java.util.ArrayList;
import java.util.HashMap;

public class MinMaxTreeDominic {

    private int searchDepth = Parameters.Depth;
   // private int searchTime; TODO

    public MinMaxTreeDominic (){
    }

    public Move initialize(ChessBoard board){
        ArrayList<Move> list = generateMoves(false, board);
        ChessBoard origin = new ChessBoard(board.getPositionsCopy());
        HashMap<Move,Integer> map = new HashMap();
        for (Move m : list){
            board.move(m.getPositionFrom(),m.getPositionTo());
            map.put(m,max(searchDepth,-100000000,100000000,board));
            board = new ChessBoard(origin.getPositionsCopy());
        }
        map.entrySet().stream().forEach(x -> {System.out.println(x.getValue() + " " + x.getKey().getPositionTo().getPiece());});
        return map.entrySet().stream().reduce((curr,next) -> curr.getValue() < next.getValue() ? curr : next).get().getKey();
    }

    public int max(int depth, int alpha, int beta, ChessBoard board) {
        int value = alpha;
        ArrayList<Move> moveset = new ArrayList<>();
        ChessBoard origin = new ChessBoard(board.getPositionsCopy());
        if (depth == 0)
            return BoardRater.getBoardRating(board);
        int maxValue = alpha;
        moveset = generateMoves(true, board);
        for (Move p : moveset) {
            board.move(p.getPositionFrom(), p.getPositionTo());
            value = min(depth - 1, maxValue, beta, board);
            board = new ChessBoard(origin.getPositionsCopy());
            if (value > maxValue) {
                maxValue = value;
                if (maxValue >= beta) {
                    break;
                }
            /*if (depth == anfangstiefe){
            gespeicherterZug = Zug;
            }
             */
            }
        }

        return maxValue;
    }

    public int min(int depth, int alpha, int beta, ChessBoard board){
        int value = beta;
        ArrayList<Move> moveset = new ArrayList<>();
        ChessBoard origin = new ChessBoard(board.getPositionsCopy());
        if (depth == 0)
            return BoardRater.getBoardRating(board);
        int minValue = beta;
        moveset = generateMoves(false, board);
        for (Move p : moveset) {
            board.move(p.getPositionFrom(), p.getPositionTo());
            value = max(depth - 1, alpha, minValue, board);
            board = new ChessBoard(origin.getPositionsCopy());
            if (value < minValue) {
                minValue = value;
                if (minValue <= alpha) {
                    break;
                }
            /*if (depth == anfangstiefe){
            gespeicherterZug = Zug;
            }
             */
            }
        }
        return minValue;
    }

    //Aktuell nur Schwarz TODO
    public ArrayList<Move> generateMoves(boolean max, ChessBoard board){
        ArrayList<Move> moveset = new ArrayList<>();
        if (max){
            for (Position p : board.getPositions()) {
                if ((p.getPiece().isWhite())) {
                    ArrayList<Position> tempMove = MoveGenerator.getMoveSet(p, board);
                    if (!(tempMove.isEmpty())) {
                        for (Position po : tempMove) {
                            moveset.add(new Move(p, po));
                        }
                    }
                }
            }

        } else {
            for (Position p : board.getPositions()) {
                if (!(p.getPiece().isWhite())) {
                    ArrayList<Position> tempMove = MoveGenerator.getMoveSet(p, board);
                    if (!(tempMove.isEmpty())) {
                        for (Position po : tempMove) {
                            moveset.add(new Move(p, po));
                        }
                    }
                }
            }
        }
        return moveset;
    }
}