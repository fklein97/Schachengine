package  MoveGenerator;
import java.util.ArrayList;
import Board.*;

/**
 * Generates the movesets of all chesspieces
 * @author Florian
 * @version 1
 */
public class MoveGenerator {
    /**
     * Returns the Moveset of a Chesspiece on a given position
     * @param currentPos current position of the chesspiece
     * @param chessboard current chessboard
     * @return moveset of the chesspiece
     */
    public static ArrayList<Position> getMoveSet(Position currentPos, ChessBoard chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        ChessPiece cp = currentPos.getPiece();

        if(cp instanceof Pawn){
            moveset = getPawnMoves(currentPos, chessboard);
        }
        else if(cp instanceof Rook){
            moveset = getRookMoves(currentPos, chessboard);
        }
        else if(cp instanceof Knight){
            moveset = getKnightMoves(currentPos, chessboard);
        }
        else if(cp instanceof Bishop){
            moveset = getBishopMoves(currentPos, chessboard);
        }
        else if(cp instanceof Queen){
            moveset = getQueenMoves(currentPos, chessboard);
        }
        else if(cp instanceof King) {
            moveset = getKingMoves(currentPos, chessboard);
        }
        else{
            new AssertionError("Unknown Chesspiece");
        }

        return moveset;
    }

    /**
     * Generates the moveset for a Pawn //TODO: implement en passant
     * @param currentPos Position of the pawn
     * @param chessboard current chessboard
     * @return moveset of the pawn
     */
    private static ArrayList<Position> getPawnMoves(Position currentPos, ChessBoard chessboard){
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
                if(leftTarget.isWhite()){
                    moveset.add(new Position(p_x - 1, p_y - 1, p));
                }
            }
            if(rightTarget != null){
                if(rightTarget.isWhite()){
                    moveset.add(new Position(p_x + 1, p_y - 1, p));
                }
            }
        }
        return moveset;
    }

    /**
     * Generates the moveset for a rook
     * @param currentPos position of the rook
     * @param chessboard current chessboard
     * @return moveset of the rook
     */
    private static ArrayList<Position> getRookMoves(Position currentPos, ChessBoard chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        ChessPiece r = currentPos.getPiece();
        int r_x = currentPos.getX();
        int r_y = currentPos.getY();

        for(int x = r_x + 1; x <= 8; x++){                              //moves right
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
        for(int x = r_x - 1; x >= 1; x--){                              //moves left
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
        for(int y = r_y + 1; y <= 8; y++){                              //moves top
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
        for(int y = r_y - 1; y >= 1; y--){                              //moves bottom
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

    /**
     * Generates the moveset for a knight
     * @param currentPos position of the knight
     * @param chessboard current chessboard
     * @return moveset of the knight
     */
    private static ArrayList<Position> getKnightMoves(Position currentPos, ChessBoard chessboard) {
        ArrayList<Position> moveset = new ArrayList<Position>();
        Knight k = (Knight) currentPos.getPiece();
        int k_x = currentPos.getX();
        int k_y = currentPos.getY();

        if ((k_x + 2 <= 8) && (k_y + 1 <= 8)){
            ChessPiece target1 = chessboard.chessPieceAt(k_x + 2, k_y + 1); //For Knight, Hardcoding just looks like try hard copy-paste
            if (target1 == null) {
                moveset.add(new Position(k_x + 2, k_y + 1, k));
            }
            else {
                if (target1.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x + 2, k_y + 1, k));
                }
            }
        }

        if ((k_x + 2 <= 8) && (k_y - 1 >= 1)) {
            ChessPiece target2 = chessboard.chessPieceAt(k_x + 2, k_y - 1);
            if (target2 == null) {
                moveset.add(new Position(k_x + 2, k_y - 1, k));
            }
            else {
                if (target2.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x + 2, k_y - 1, k));
                }
            }
        }

        if ((k_x - 2 >= 1) && (k_y + 1 <= 8)) {
            ChessPiece target3 = chessboard.chessPieceAt(k_x - 2, k_y + 1);
            if (target3 == null) {
                moveset.add(new Position(k_x - 2, k_y + 1, k));
            }
            else {
                if (target3.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x - 2, k_y + 1, k));
                }
            }
        }

        if ((k_x - 2 >= 1) && (k_y - 1 >= 1)) {
            ChessPiece target4 = chessboard.chessPieceAt(k_x - 2, k_y - 1);
            if (target4 == null) {
                moveset.add(new Position(k_x - 2, k_y - 1, k));
            }
            else {
                if (target4.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x - 2, k_y - 1, k));
                }
            }
        }

        if ((k_x + 1 <= 8) && (k_y + 2 <= 8)) {
            ChessPiece target5 = chessboard.chessPieceAt(k_x + 1, k_y + 2);
            if (target5 == null) {
                moveset.add(new Position(k_x + 1, k_y + 2, k));
            }
            else {
                if (target5.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x + 1, k_y + 2, k));
                }
            }
        }

        if ((k_x + 1 <= 8) && (k_y - 2 >= 1)) {
            ChessPiece target6 = chessboard.chessPieceAt(k_x + 1, k_y - 2);
            if (target6 == null) {
                moveset.add(new Position(k_x + 1, k_y - 2, k));
            }
            else {
                if (target6.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x + 1, k_y - 2, k));
                }
            }
        }

        if ((k_x - 1 >= 1) && (k_y + 2 <= 8)) {
            ChessPiece target7 = chessboard.chessPieceAt(k_x - 1, k_y + 2);
            if (target7 == null) {
                moveset.add(new Position(k_x - 1, k_y + 2, k));
            }
            else {
                if (target7.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x - 1, k_y + 2, k));
                }
            }
        }

        if ((k_x - 1 >= 1) && (k_y - 2 >= 1)) {
            ChessPiece target8 = chessboard.chessPieceAt(k_x - 1, k_y - 2);
            if (target8 == null) {
                moveset.add(new Position(k_x - 1, k_y - 2, k));
            }
            else {
                if (target8.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x - 1, k_y - 2, k));
                }
            }
        }

        return moveset;
    }

    /**
     * Generates the moveset for a bishop
     * @param currentPos position of the bishop
     * @param chessboard current chessboard
     * @return moveset of the bishop
     */
    private static ArrayList<Position> getBishopMoves(Position currentPos, ChessBoard chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        ChessPiece b = currentPos.getPiece();
        int b_x = currentPos.getX();
        int b_y = currentPos.getY();

        int i_x;
        int i_y;

        i_x = b_x + 1;
        i_y = b_y + 1;
        while((i_x <= 8) && (i_y <= 8)){                            //moves top-right
            ChessPiece target = chessboard.chessPieceAt(i_x,i_y);
            if(target == null){
                moveset.add(new Position(i_x,i_y,b));
            }
            else{
                if(target.isWhite() != b.isWhite()){
                    moveset.add(new Position(i_x,i_y,b));
                }
                break;
            }
            i_x++;
            i_y++;
        }

        i_x = b_x + 1;
        i_y = b_y - 1;
        while((i_x <= 8) && (i_y >= 1)){                            //moves bottom-right
            ChessPiece target = chessboard.chessPieceAt(i_x,i_y);
            if(target == null){
                moveset.add(new Position(i_x,i_y,b));
            }
            else{
                if(target.isWhite() != b.isWhite()){
                    moveset.add(new Position(i_x,i_y,b));
                }
                break;
            }
            i_x++;
            i_y--;
        }

        i_x = b_x - 1;
        i_y = b_y + 1;
        while((i_x >= 1) && (i_y <= 8)){                            //moves top-left
            ChessPiece target = chessboard.chessPieceAt(i_x,i_y);
            if(target == null){
                moveset.add(new Position(i_x,i_y,b));
            }
            else{
                if(target.isWhite() != b.isWhite()){
                    moveset.add(new Position(i_x,i_y,b));
                }
                break;
            }
            i_x--;
            i_y++;
        }

        i_x = b_x - 1;
        i_y = b_y - 1;
        while((i_x >= 1) && (i_y >= 1)){                            //moves bottom-left
            ChessPiece target = chessboard.chessPieceAt(i_x,i_y);
            if(target == null){
                moveset.add(new Position(i_x,i_y,b));
            }
            else{
                if(target.isWhite() != b.isWhite()){
                    moveset.add(new Position(i_x,i_y,b));
                }
                break;
            }
            i_x--;
            i_y--;
        }

        return moveset;
    }

    /**
     * Generates the moveset for a queen
     * @param currentPos position of the queen
     * @param chessboard current chessboard
     * @return moveset of the queen
     */
    private static ArrayList<Position> getQueenMoves(Position currentPos, ChessBoard chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        moveset.addAll(getRookMoves(currentPos,chessboard));                                        //@all: too lazy or ok?
        moveset.addAll(getBishopMoves(currentPos,chessboard));

        return moveset;
    }

    /**
     * Generates the moveset for a king
     * @param currentPos position of the king
     * @param chessboard current chessboard
     * @return moveset of the king
     */
    private static ArrayList<Position> getKingMoves(Position currentPos, ChessBoard chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        King k = (King)currentPos.getPiece();
        int k_x = currentPos.getX();
        int k_y = currentPos.getY();
        ChessPiece target;

        if(k_x + 1 <= 8) {                                                  //King is also sad hardcoding
            target = chessboard.chessPieceAt(k_x + 1, k_y);
            if (target == null) {
                moveset.add(new Position(k_x + 1, k_y, k));
            }
            else{
                if (target.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x + 1, k_y, k));
                }
            }
        }

        if(k_x -1 >= 1) {
            target = chessboard.chessPieceAt(k_x - 1, k_y);
            if (target == null) {
                moveset.add(new Position(k_x - 1, k_y, k));
            }
            else{
                if (target.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x - 1, k_y, k));
                }
            }
        }

        if(k_y + 1 <= 8) {
            target = chessboard.chessPieceAt(k_x, k_y + 1);
            if (target == null) {
                moveset.add(new Position(k_x, k_y + 1, k));
            }
            else{
                if (target.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x, k_y + 1, k));
                }
            }
        }

        if(k_y - 1 >= 1) {
            target = chessboard.chessPieceAt(k_x, k_y-1);
            if (target == null) {
                moveset.add(new Position(k_x, k_y-1, k));
            }
            else{
                if (target.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x, k_y-1, k));
                }
            }
        }

        if((k_x + 1 <= 8) && (k_y + 1 <= 8)) {
            target = chessboard.chessPieceAt(k_x + 1, k_y + 1);
            if (target == null) {
                moveset.add(new Position(k_x + 1, k_y + 1, k));
            }
            else{
                if (target.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x + 1, k_y + 1, k));
                }
            }
        }

        if((k_x + 1 <= 8) && (k_y - 1 >= 1)) {
            target = chessboard.chessPieceAt(k_x + 1, k_y - 1);
            if (target == null) {
                moveset.add(new Position(k_x + 1, k_y - 1, k));
            }
            else{
                if (target.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x + 1, k_y - 1, k));
                }
            }
        }

        if((k_x - 1 >= 1) && (k_y + 1 <= 8)) {
            target = chessboard.chessPieceAt(k_x - 1, k_y + 1);
            if (target == null) {
                moveset.add(new Position(k_x - 1, k_y + 1, k));
            }
            else{
                if (target.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x - 1, k_y + 1, k));
                }
            }
        }

        if((k_x - 1 >= 1) && (k_y - 1 >= 1)) {
            target = chessboard.chessPieceAt(k_x - 1, k_y - 1);
            if (target == null) {
                moveset.add(new Position(k_x - 1, k_y - 1, k));
            }
            else{
                if (target.isWhite() != k.isWhite()) {
                    moveset.add(new Position(k_x - 1, k_y - 1, k));
                }
            }
        }

        return moveset;
    }
}