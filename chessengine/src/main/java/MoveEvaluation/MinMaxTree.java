package MoveEvaluation;

import Board.*;
import MoveGenerator.*;
import Parameters.Parameters;
import Rating.BoardRater;
import UCI.IO;
import UCI.OperationManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * MinMaxTree with AlphaBeta-Pruning to calculate the best moves
 * @author Dominic Büch, Florian Klein
 */
public class MinMaxTree {

    private int searchDepth = Parameters.Depth;
    private ArrayList<Move> killmoves = new ArrayList<>();
   // private int searchTime; TODO

    public MinMaxTree(){
    }

    /**
     * Starts the calculation to get the best move
     * @param board ChessBoard
     * @param max true = calculate for white, false = calculate for black
     * @return best move
     */
    public Move initialize(ChessBoard board, boolean max){
        int alpha = -100000000;
        int beta = 100000000;
        ArrayList<Move> list = generateMoves(max, board);
        ArrayList<Move> sortedlist = getPreSortedMoves(list,board,max);
        ChessBoard origin = new ChessBoard(board.getPositionsCopy());
        HashMap<Move,Integer> map = new HashMap();
        for (Move m : sortedlist){
            board.move(m.getPositionFrom(),m.getPositionTo());
            if(max) {
                int min_value = min(searchDepth - 1, alpha, beta,board);
                map.put(m, min_value);
                if(min_value < alpha){
                    alpha = min_value;
                }
            }
            else{
                int max_value =  max(searchDepth - 1, alpha, beta, board);
                map.put(m,max_value);
                if(max_value > beta){
                    beta = max_value;
                }
            }
            board = new ChessBoard(origin.getPositionsCopy());

            IO.answer("info currmove " + OperationManager.posToString(m.getPositionFrom()) + OperationManager.posToString(m.getPositionTo()) + " currmovenumber " + sortedlist.indexOf(m));
        }
        map.entrySet().stream().forEach(x -> {IO.sendDebugInfo(x.getValue() + " " + x.getKey().getPositionTo().getPiece());});
        if(max){
            return map.entrySet().stream().reduce((curr, next) -> curr.getValue() > next.getValue() ? curr : next).get().getKey();
        }
        else {
            return map.entrySet().stream().reduce((curr, next) -> curr.getValue() < next.getValue() ? curr : next).get().getKey();
        }
    }

    /**
     * Sorts the moves for a calculation (uses killermoves and rating sort) based on selectionsort
     * @param movelist Moves that should be sorted
     * @param board ChessBoard
     * @param max true = sort for white, false = sort for black
     * @return
     */
    private ArrayList<Move> getPreSortedMoves(ArrayList<Move> movelist, ChessBoard board, boolean max){
        ArrayList<Move> arekillmoves = new ArrayList<>();
        for(Move m : movelist){
            if(killmoves.contains(m)){
                arekillmoves.add(m);
            }

        }
        movelist.removeAll(arekillmoves);

        Move movearray[] = movelist.toArray(new Move[movelist.size()]);

        int max_i = movearray.length -1;
        int insert_i = 0;
        while(insert_i < max_i){
            int bestPosition = insert_i;
            for(int i = insert_i + 1; i <= max_i; i++){
                ChessBoard sortboard = new ChessBoard(board.getPositionsCopy());
                ChessBoard sortboardbest = new ChessBoard(board.getPositionsCopy());
                sortboard.move(movearray[i].getPositionFrom(),movearray[i].getPositionTo());
                sortboardbest.move(movearray[bestPosition].getPositionFrom(),movearray[bestPosition].getPositionTo());
                int rating = BoardRater.getBoardRating(sortboard);
                int bestrating = BoardRater.getBoardRating(sortboardbest);
                if(max){
                    if (rating > bestrating) {
                        bestPosition = i;
                    }
                }
                else {
                    if (rating < bestrating) {
                        bestPosition = i;
                    }
                }
            }
            movearray = swap(movearray, bestPosition, insert_i);
            insert_i++;
        }
        ArrayList<Move> sortedmoves = arekillmoves;
        sortedmoves.addAll(new ArrayList<Move>(Arrays.asList(movearray)));

        return sortedmoves;
    }

    /**
     * Swaps to moves in a movearray (used for presort)
     * @param movearray movearray
     * @param i index of the first move that should be swapped
     * @param j index of the second move that should be swapped
     * @return movearray with swapped moves
     */
    private Move[] swap(Move[] movearray, int i, int j){
        Move temp = null;
        temp = movearray[i];
        movearray[i] = movearray[j];
        movearray[j] = temp;
        return movearray;
    }

    /**
     *
     * @param depth
     * @param alpha
     * @param beta
     * @param board
     * @return
     */
    private int max(int depth, int alpha, int beta, ChessBoard board) {
        int value = alpha;
        ArrayList<Move> moveset = new ArrayList<>();
        ChessBoard origin = new ChessBoard(board.getPositionsCopy());
        if (depth == 0)
            return BoardRater.getBoardRating(board);
        int maxValue = alpha;
        moveset = generateMoves(true, board);
        if(depth == Parameters.Depth - 1){
            moveset = getPreSortedMoves(moveset,board,true);
        }
        for (Move p : moveset) {
            board.move(p.getPositionFrom(), p.getPositionTo());
            value = min(depth - 1, maxValue, beta, board);
            board = new ChessBoard(origin.getPositionsCopy());
            if (value > maxValue) {
                maxValue = value;
                if (maxValue >= beta && Parameters.useAlphaBeta) {
                    if(killmoves.contains(p) == false) {
                        killmoves.add(p);
                    }
                    break;
                }
                else{
                    if(killmoves.contains(p) == true){
                        killmoves.remove(p);
                    }
                }
            /*if (depth == anfangstiefe){
            gespeicherterZug = Zug;
            }
             */
            }
        }

        return maxValue;
    }

    /**
     *
     * @param depth
     * @param alpha
     * @param beta
     * @param board
     * @return
     */
    private int min(int depth, int alpha, int beta, ChessBoard board){
        int value = beta;
        ArrayList<Move> moveset = new ArrayList<>();
        ChessBoard origin = new ChessBoard(board.getPositionsCopy());
        if (depth == 0)
            return BoardRater.getBoardRating(board);
        int minValue = beta;
        moveset = generateMoves(false, board);
        if(depth == Parameters.Depth - 1){
            moveset = getPreSortedMoves(moveset,board,false);
        }
        for (Move p : moveset) {
            board.move(p.getPositionFrom(), p.getPositionTo());
            value = max(depth - 1, alpha, minValue, board);
            board = new ChessBoard(origin.getPositionsCopy());
            if (value < minValue) {
                minValue = value;
                if (minValue <= alpha && Parameters.useAlphaBeta) {
                    if(killmoves.contains(p) == false) {
                        killmoves.add(p);
                    }
                    break;
                }
                else{
                    if(killmoves.contains(p) == true){
                        killmoves.remove(p);
                    }
                }
            /*if (depth == anfangstiefe){
            gespeicherterZug = Zug;
            }
             */
            }
        }
        return minValue;
    }

    /**
     * Generates all moves for a color
     * @param max true = for white, false = for black
     * @param board ChessBoard
     * @return generated moves
     */
    private ArrayList<Move> generateMoves(boolean max, ChessBoard board){
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