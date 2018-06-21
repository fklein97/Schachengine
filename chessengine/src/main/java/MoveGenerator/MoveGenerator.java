package  MoveGenerator;
import java.util.ArrayList;
import Board.*;

/**
 * Created by FKPro on 18.06.2018.
 */
public class MoveGenerator {
    //TODO: get the real board as soon as @Dominic makes a public variable or getter
    private static ChessBoard chessboard =new ChessBoard();


    /**
     * Returns the Moveset of a Chesspiece on a given position
     * @param currentPos current position of the chesspiece
     * @return moveset of the chesspiece
     */
    public static ArrayList<Position> getMoveSet(Position currentPos){
        ArrayList<Position> moveset = new ArrayList<Position>();
        ChessPiece cp = currentPos.getPiece();

        if(cp instanceof Pawn){
            moveset = getPawnMoves(currentPos);
        }
        else if(cp instanceof Rook){
            moveset = getRookMoves(currentPos);
        }

        return moveset;
    }

    /**
     * Generates the moveset for a Pawn //TODO: implement en passant
     * @param currentPos Position of the pawn
     * @return moveset of the pawn
     */
    private static ArrayList<Position> getPawnMoves(Position currentPos){
        ArrayList<Position> moveset = new ArrayList<Position>();
        Pawn p = (Pawn)currentPos.getPiece();
        int p_x = currentPos.getX();
        int p_y = currentPos.getY();

        if(p.isWhite() == true){        // WHITE PAWN
            if((p_y + 1 <= 8) && (chessboard.chessPieceAt(p_x,p_y+1) == null)) { //Check if legal move
                moveset.add(new Position(p_x, p_y + 1, p));
                if (p.moved() == false) { //has moved?
                    if ((p_y + 2 <= 8) && (chessboard.chessPieceAt(p_x, p_y + 2) == null)) { //Check if legal move
                        moveset.add(new Position(p_x, p_y + 2, p));
                    }
                }
            }
            //beat moves
            ChessPiece leftTarget = chessboard.chessPieceAt(p_x - 1, p_y + 1);
            ChessPiece rightTarget = chessboard.chessPieceAt(p_x + 1, p_y + 1);
            if(leftTarget != null){
                if(leftTarget.isWhite() == false){
                    moveset.add(new Position(p_x - 1, p_y + 1, p));
                }
            }
            if(rightTarget != null){
                if(rightTarget.isWhite() == false){
                    moveset.add(new Position(p_x + 1, p_y + 1, p));
                }
            }
        }
        else{                           // BLACK PAWN
            if((p_y - 1 >= 1) && (chessboard.chessPieceAt(p_x,p_y-1) == null)) { //Check if legal move
                moveset.add(new Position(p_x, p_y - 1, p));
                if (p.moved() == false) { //has moved?
                    if ((p_y - 2 >= 1) && (chessboard.chessPieceAt(p_x, p_y - 2) == null)) { //Check if legal move
                        moveset.add(new Position(p_x, p_y - 2, p));
                    }
                }
            }
            //beat moves
            ChessPiece leftTarget = chessboard.chessPieceAt(p_x - 1, p_y - 1);
            ChessPiece rightTarget = chessboard.chessPieceAt(p_x + 1, p_y - 1);
            if(leftTarget != null){
                if(leftTarget.isWhite() == true){
                    moveset.add(new Position(p_x - 1, p_y - 1, p));
                }
            }
            if(rightTarget != null){
                if(rightTarget.isWhite() == true){
                    moveset.add(new Position(p_x + 1, p_y - 1, p));
                }
            }
        }
        return moveset;
    }

    /**
     * Generates the moveset for a rook
     * @param currentPos position of the rook
     * @return moveset of the rook
     */
    private static ArrayList<Position> getRookMoves(Position currentPos){
        ArrayList<Position> moveset = new ArrayList<Position>();
        Rook r = (Rook)currentPos.getPiece();
        int r_x = currentPos.getX();
        int r_y = currentPos.getY();

        for(int x = r_x; x <= 8; x++){
            ChessPiece target = chessboard.chessPieceAt(x,r_y);
            if(target == null) {
                moveset.add(new Position(x, r_y, r));
            }
            else{
                if(target.isWhite() != r.isWhite()){
                    moveset.add(new Position(x, r_y, r));
                }
                break;
            }
        }

        for(int x = r_x; x >= 1; x--){
            ChessPiece target = chessboard.chessPieceAt(x,r_y);
            if(target == null) {
                moveset.add(new Position(x, r_y, r));
            }
            else{
                if(target.isWhite() != r.isWhite()){
                    moveset.add(new Position(x, r_y, r));
                }
                break;
            }
        }

        for(int y = r_y; y <= 8; y++){
            ChessPiece target = chessboard.chessPieceAt(r_x,y);
            if(target == null) {
                moveset.add(new Position(r_x, y, r));
            }
            else{
                if(target.isWhite() != r.isWhite()){
                    moveset.add(new Position(r_x, y, r));
                }
                break;
            }
        }

        for(int y = r_y; y >= 1; y++){
            ChessPiece target = chessboard.chessPieceAt(r_x,y);
            if(target == null) {
                moveset.add(new Position(r_x, y, r));
            }
            else{
                if(target.isWhite() != r.isWhite()){
                    moveset.add(new Position(r_x, y, r));
                }
                break;
            }
        }

        return moveset;
    }
}