package  MoveGenerator;
import java.util.ArrayList;
import Board.*;
import Parameters.Strings;

/**
 * Generates the movesets of all chesspieces
 * @author Florian Klein
 */
public class MoveGenerator {
    /**
     * Returns the Moveset of a Chesspiece on a given position
     * @param currentPos current position of the chesspiece
     * @param chessboard current chessboard
     * @return moveset of the chesspiece
     */
    public static ArrayList<Position> getMoveSet(Position currentPos, ChessBoard chessboard){
        return getMoveSet(currentPos,chessboard,chessboard);
    }

    /**
     * Returns the Moveset of a Chesspiece on a given position
     * @param currentPos current position of the chesspiece
     * @param chessboard current chessboard
     * @param old_chessboard chessboard one move ago (en passant moves)
     * @return moveset of the chesspiece
     */
    public static ArrayList<Position> getMoveSet(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        ChessPiece cp = currentPos.getPiece();

        if(cp instanceof Pawn){
            moveset = getPawnMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Rook){
            moveset = getRookMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Knight){
            moveset = getKnightMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Bishop){
            moveset = getBishopMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Queen){
            moveset = getQueenMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof King) {
            moveset = getKingMoves(currentPos, chessboard, old_chessboard);
        }
        else{
            throw new AssertionError("Unknown Chesspiece");
        }

        ArrayList<Position> dangermoves = new ArrayList<>();
        for(Position p : moveset){
            Position movefrom = new Position(currentPos.getX(),currentPos.getY(), cp);
            Position moveto = new Position(p.getX(),p.getY(),cp);
            ArrayList<Position> boardPositions = chessboard.getPositionsCopy();
            ChessBoard checkDangerBoard = new ChessBoard(boardPositions);
            checkDangerBoard.move(movefrom,moveto);
            if(checkDangerBoard.isKinginDanger(cp.isWhite())){
                dangermoves.add(p);
            }
        }
        moveset.removeAll(dangermoves);

        return moveset;
    }

    /**
     * Returns the Moveset of a Chesspiece on a given position without looking for DangerPositions
     * @param currentPos current position of the chesspiece
     * @param chessboard current chessboard
     * @param old_chessboard chessboard one move ago (en passant moves)
     * @return moveset of the chesspiece
     */
    public static ArrayList<Position> getMoveSetwithoutDangerCheck(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        ChessPiece cp = currentPos.getPiece();

        if(cp instanceof Pawn){
            moveset = getPawnMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Rook){
            moveset = getRookMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Knight){
            moveset = getKnightMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Bishop){
            moveset = getBishopMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Queen){
            moveset = getQueenMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof King) {
            moveset = getKingMoves(currentPos, chessboard, old_chessboard);
        }
        else{
            throw new AssertionError(Strings.UNKNOWN_CHESSPIECE);
        }

        return moveset;
    }

    /**
     * Returns the Beatmoveset of a Chesspiece on a given position without looking for DangerPositions
     * @param currentPos current position of the chesspiece
     * @param chessboard current chessboard
     * @param old_chessboard chessboard one move ago (en passant moves)
     * @return moveset of the chesspiece
     */
    public static ArrayList<Position> getBeatMoveSetwithoutDangerCheck(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        ChessPiece cp = currentPos.getPiece();

        if(cp instanceof Pawn){
            moveset = getPawnBeatMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Rook){
            moveset = getRookMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Knight){
            moveset = getKnightMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Bishop){
            moveset = getBishopMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof Queen){
            moveset = getQueenMoves(currentPos, chessboard, old_chessboard);
        }
        else if(cp instanceof King) {
            moveset = getKingMoves(currentPos, chessboard, old_chessboard);
        }
        else{
            throw new AssertionError(Strings.UNKNOWN_CHESSPIECE);
        }

        return moveset;
    }

    /**
     * Generates the moveset for a Pawn
     * @param currentPos Position of the pawn
     * @param chessboard current chessboard
     * @param old_chessboard
     * @return moveset of the pawn
     */
    private static ArrayList<Position> getPawnMoves(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        Pawn p = (Pawn)currentPos.getPiece();
        int p_x = currentPos.getX();
        int p_y = currentPos.getY();

        if(p.isWhite() == true){        // WHITE PAWN
            if((p_y + 1 <= 8) && (chessboard.chessPieceAt(p_x,p_y+1) == null)) { //Check if legal move
                if(p_y + 1 < 8) {
                    moveset.add(new Position(p_x, p_y + 1, p));
                }
                else{ //Pawn Transformation
                    moveset.add(new Position(p_x, p_y + 1, new Queen(true)));
                    moveset.add(new Position(p_x, p_y + 1, new Rook(true)));
                    moveset.add(new Position(p_x, p_y + 1, new Knight(true)));
                    moveset.add(new Position(p_x, p_y + 1, new Bishop(true)));
                }
                if (p.moved() == false && p_y == 2) { //is on startposition
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

            //en passant
            ChessPiece leftPassant = chessboard.chessPieceAt(p_x-1,p_y);
            if(leftPassant != null){
                if(leftPassant instanceof Pawn && leftPassant.isWhite() == false){
                    if(leftPassant == old_chessboard.chessPieceAt(p_x-1,p_y + 2)){
                        moveset.add(new Position(p_x - 1, p_y +1,p));
                    }
                }
            }
            ChessPiece rightPassant = chessboard.chessPieceAt(p_x+1,p_y);
            if(rightPassant != null){
                if(rightPassant instanceof Pawn && rightPassant.isWhite() == false){
                    if(rightPassant == old_chessboard.chessPieceAt(p_x+1,p_y + 2)){
                        moveset.add(new Position(p_x + 1, p_y +1,p));
                    }
                }
            }
        }
        else{                           // BLACK PAWN
            if((p_y - 1 >= 1) && (chessboard.chessPieceAt(p_x,p_y-1) == null)) { //Check if legal move
                if(p_y - 1 > 1) {
                    moveset.add(new Position(p_x, p_y - 1, p));
                }
                else{ //Pawn Transformation
                    moveset.add(new Position(p_x, p_y - 1, new Queen(false)));
                    moveset.add(new Position(p_x, p_y - 1, new Rook(false)));
                    moveset.add(new Position(p_x, p_y - 1, new Knight(false)));
                    moveset.add(new Position(p_x, p_y - 1, new Bishop(false)));
                }
                if (p.moved() == false && p_y == 7) { //is on startposition
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

            //en passant
            ChessPiece leftPassant = chessboard.chessPieceAt(p_x-1,p_y);
            if(leftPassant != null){
                if(leftPassant instanceof Pawn && leftPassant.isWhite() == true){
                    if(leftPassant == old_chessboard.chessPieceAt(p_x-1,p_y - 2)){
                        moveset.add(new Position(p_x - 1, p_y -1,p));
                    }
                }
            }
            ChessPiece rightPassant = chessboard.chessPieceAt(p_x+1,p_y);
            if(rightPassant != null){
                if(rightPassant instanceof Pawn && rightPassant.isWhite() == true){
                    if(rightPassant == old_chessboard.chessPieceAt(p_x+1,p_y - 2)){
                        moveset.add(new Position(p_x + 1, p_y -1,p));
                    }
                }
            }
        }
        return moveset;
    }

    /**
     * Generates the Beatmoveset for a Pawn
     * @param currentPos Position of the pawn
     * @param chessboard current chessboard
     * @param old_chessboard
     * @return moveset of the pawn
     */
    private static ArrayList<Position> getPawnBeatMoves(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        Pawn p = (Pawn)currentPos.getPiece();
        int p_x = currentPos.getX();
        int p_y = currentPos.getY();

        if(p.isWhite() == true) {        // WHITE PAWN
            //beat moves
            ChessPiece leftTarget = chessboard.chessPieceAt(p_x - 1, p_y + 1);
            ChessPiece rightTarget = chessboard.chessPieceAt(p_x + 1, p_y + 1);
            if (p_x - 1 >= 1 && p_y + 1 <= 8) {
                if (leftTarget != null) {
                    if (leftTarget.isWhite() == false) {
                        moveset.add(new Position(p_x - 1, p_y + 1, p));
                    }
                } else {
                    moveset.add(new Position(p_x - 1, p_y + 1, p));
                }
            }
            if (p_x + 1 <= 8 && p_y + 1 <= 8){
                if (rightTarget != null) {
                    if (rightTarget.isWhite() == false) {
                        moveset.add(new Position(p_x + 1, p_y + 1, p));
                    }
                } else {
                    moveset.add(new Position(p_x + 1, p_y + 1, p));
                }
            }
            //en passant
            ChessPiece leftPassant = chessboard.chessPieceAt(p_x-1,p_y);
            if(leftPassant != null){
                if(leftPassant instanceof Pawn && leftPassant.isWhite() == false){
                    if(leftPassant == old_chessboard.chessPieceAt(p_x-1,p_y + 2)){
                        moveset.add(new Position(p_x - 1, p_y +1,p));
                    }
                }
            }
            ChessPiece rightPassant = chessboard.chessPieceAt(p_x+1,p_y);
            if(rightPassant != null){
                if(rightPassant instanceof Pawn && rightPassant.isWhite() == false){
                    if(rightPassant == old_chessboard.chessPieceAt(p_x+1,p_y + 2)){
                        moveset.add(new Position(p_x + 1, p_y +1,p));
                    }
                }
            }
        }
        else{                           // BLACK PAWN
            //beat moves
            ChessPiece leftTarget = chessboard.chessPieceAt(p_x - 1, p_y - 1);
            ChessPiece rightTarget = chessboard.chessPieceAt(p_x + 1, p_y - 1);
            if(p_x - 1 >= 1 && p_y - 1 >= 1) {
                if (leftTarget != null) {
                    if (leftTarget.isWhite()) {
                        moveset.add(new Position(p_x - 1, p_y - 1, p));
                    }
                } else {
                    moveset.add(new Position(p_x - 1, p_y - 1, p));
                }
            }
            if(p_x + 1 <= 8 && p_y - 1 >= 1) {
                if (rightTarget != null) {
                    if (rightTarget.isWhite()) {
                        moveset.add(new Position(p_x + 1, p_y - 1, p));
                    }
                } else {
                    moveset.add(new Position(p_x + 1, p_y - 1, p));
                }
            }
            //en passant
            ChessPiece leftPassant = chessboard.chessPieceAt(p_x-1,p_y);
            if(leftPassant != null){
                if(leftPassant instanceof Pawn && leftPassant.isWhite() == true){
                    if(leftPassant == old_chessboard.chessPieceAt(p_x-1,p_y - 2)){
                        moveset.add(new Position(p_x - 1, p_y -1,p));
                    }
                }
            }
            ChessPiece rightPassant = chessboard.chessPieceAt(p_x+1,p_y);
            if(rightPassant != null){
                if(rightPassant instanceof Pawn && rightPassant.isWhite() == true){
                    if(rightPassant == old_chessboard.chessPieceAt(p_x+1,p_y - 2)){
                        moveset.add(new Position(p_x + 1, p_y -1,p));
                    }
                }
            }
        }
        return moveset;
    }

    /**
     * Generates the moveset for a rook
     * @param currentPos position of the rook
     * @param chessboard current chessboard
     * @param old_chessboard
     * @return moveset of the rook
     */
    private static ArrayList<Position> getRookMoves(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard){
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
     * @param old_chessboard
     * @return moveset of the knight
     */
    private static ArrayList<Position> getKnightMoves(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard) {
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
     * @param old_chessboard
     * @return moveset of the bishop
     */
    private static ArrayList<Position> getBishopMoves(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard){
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
     * @param old_chessboard
     * @return moveset of the queen
     */
    private static ArrayList<Position> getQueenMoves(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        moveset.addAll(getRookMoves(currentPos,chessboard, old_chessboard));                                        //@all: too lazy or ok?
        moveset.addAll(getBishopMoves(currentPos,chessboard, old_chessboard));

        return moveset;
    }

    /**
     * Generates the moveset for a king
     * @param currentPos position of the king
     * @param chessboard current chessboard
     * @param old_chessboard
     * @return moveset of the king
     */
    private static ArrayList<Position> getKingMoves(Position currentPos, ChessBoard chessboard, ChessBoard old_chessboard){
        ArrayList<Position> moveset = new ArrayList<Position>();
        King k = (King)currentPos.getPiece();
        int k_x = currentPos.getX();
        int k_y = currentPos.getY();
        ChessPiece target;

        //ArrayList<Position> dangers = DangerChecker.getDangerPositions(chessboard,k.isWhite());

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

        /**ArrayList<Position> dangermoves = new ArrayList<>();
        for(Position d: dangers){ //prevents that a king moves in a danger position
            for(Position p: moveset){
                if(p.getX() == d.getX() && p.getY() == d.getY()){
                    dangermoves.add(p);
                }
            }
        }
        moveset.removeAll(dangermoves);
         */
        return moveset;
    }
}