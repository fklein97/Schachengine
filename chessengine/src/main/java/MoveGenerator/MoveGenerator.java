package  MoveGenerator;
import java.util.ArrayList;
import Board.*;

/**
 * Created by FKPro on 18.06.2018.
 */
public class MoveGenerator {

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
     * Generates the moveset for a Pawn
     * @param currentPos Position of the pawn
     * @return moveset of the pawn
     */
    private static ArrayList<Position> getPawnMoves(Position currentPos){
        ArrayList<Position> moveset = new ArrayList<Position>();
        Pawn p = (Pawn)currentPos.getPiece();

        if(p.isWhite() == true){ // Pawn is white
            if((currentPos.getY() + 1 <= 8) && ) { //Check if legal move
                moveset.add(new Position(currentPos.getX(), currentPos.getY() + 1, p));
            }
            if((p.moved() == false) && (currentPos.getY() + 2 <= 8)) {
                moveset.add(new Position(currentPos.getX(), currentPos.getY() + 2, p));
            }
            return moveset;
        }
        else{                           // Pawn is black
            if(currentPos.getY() - 1 >= 1) {
                moveset.add(new Position(currentPos.getX(), currentPos.getY() - 1, p));
            }
            if((p.moved() == false) && (currentPos.getY() - 2 >= 1)) {
                moveset.add(new Position(currentPos.getX(), currentPos.getY() - 2, p));
            }
            return moveset;
        }
    }

    /**
     * Generates the moveset for a rook
     * @param currentPos position of the rook
     * @return moveset of the rook
     */
    private static ArrayList<Position> getRookMoves(Position currentPos){
        ArrayList<Position> moveset = new ArrayList<Position>();
        Rook r = (Rook)currentPos.getPiece();



        for(int x = currentPos.getX(); x <= 8; x++){
            moveset.add(new Position(x,currentPos.getY(), r));
        }

        for(int x = currentPos.getX(); x >= 1; x--){
            moveset.add(new Position(x,currentPos.getY(), r));
        }

        for(int y = currentPos.getY(); y <= 8; y++){
            moveset.add(new Position(currentPos.getX(),y, r));
        }

        for(int y = currentPos.getX(); y >= 1; y++){
            moveset.add(new Position(currentPos.getX(),y, r));
        }

        return moveset;
    }
}