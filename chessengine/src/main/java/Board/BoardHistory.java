package Board;

import java.util.*;

public class BoardHistory {

    private static ArrayList<ChessBoard>  boards = new ArrayList<ChessBoard>();

    public static void add(ChessBoard chessBoard){
        boards.add(chessBoard);
    }

    public static void rewind(){
        if(boards.size() > 0){
            boards.remove((boards.size()-1));

        }
    }

    public static void clear(){
        boards = new ArrayList<ChessBoard>();
    }

    public static boolean draw(){
        boolean draw    = false;
        int duplicates  = 0;

        for(int i = 0; i < boards.size(); i++){
            for(int j = i+1; j < boards.size(); j++){
                if(boards.get(i).equals(boards.get(j))){
                    duplicates++;
                }
            }
            if(duplicates >= 2){
                return true;
            }
            else{
                duplicates = 0;
            }
        }

        return draw;
    }


    public static void add(Position from, Position to){
        ChessBoard chessBoard = new ChessBoard(boards.get((boards.size()-1)).getPositionsCopy());
        if(from.toString().equals("white pawn") || from.toString().equals("black pawn")){
            boards.clear();
        }
        chessBoard.move(from, to);
        boards.add(chessBoard);
    }

}
