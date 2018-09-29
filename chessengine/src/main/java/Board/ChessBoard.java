package Board;

import MoveGenerator.DangerChecker;
import Parameters.Strings;
import UCI.IO;

import java.util.*;

/**
 * ChessBoard Objects manage a Chessboard
 */
public class ChessBoard {

    private final static int NULL = 0;

    private ArrayList<Position> positions;

    /**
     * Returns all Positions inside this ChessBoard
     * @return ArrayList that contains all Positions inside this Chessboard
     */
    public ArrayList<Position> getPositions(){
        return positions;
    }

    /**
     * Returns copies of all Positions inside this Chessboard
     * @return ArrayList that contains copies of all Positions inside this Chessboard
     */
    public ArrayList<Position> getPositionsCopy() {
        ArrayList<Position> pos = new ArrayList<Position>();
        for(Position p : positions){
            pos.add(new Position(p.getX(),p.getY(),p.getPieceCopy()));
        }

        return pos;
    }

    /**
     * Overrides the Positions of this Chessboard
     * @param positions new Positions the board should be overwritten with
     */
    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }

    /**
     * Constructs a chessboard with the standart positions
     */
    public ChessBoard() {
        positions = new ArrayList<Position>();

        setUp();
    }

    /**
     * Constructs a chessboard with the given positions
     * @param positions Positions of the new board
     */
    public ChessBoard(ArrayList<Position> positions) {
        this.positions = positions;
    }

    public Position getKingPosition(boolean forWhite) {
        for (Position p : positions) {
            if (p.getPiece().isWhite() == forWhite && p.getPiece() instanceof King) {
                return p;
            }
        }

        return null;
    }

    /**
     * Checks if a given position on the board is taken or not
     * @param x x-Parameter of the position
     * @param y y-Parameter of the position
     * @return true, if the position is taken, false, if not
     */
    public boolean positionIsTaken(int x, int y) {
        ChessPiece chessPiece = chessPieceAt(x, y);
        if (chessPiece != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns the ChessPiecce on a given position
     * @param x x-Parameter of the position
     * @param y y-Parameter of the position
     * @return ChessPiece on the given Position or null if position is not taken
     */
    public ChessPiece chessPieceAt(int x, int y) {
        for(Position p : positions){
            if(p.getX() == x && p.getY() == y){
                return p.getPiece();
            }
        }

        return null;
    }

    public void newGame() {
        setUp();
    }

    public void newGame(ArrayList<Position> positions) {
        this.positions = positions;
    }
    /**
     * prints this chessboard
     */
    public void print() {
        System.out.print(Strings.DEBUG_INFO + Strings.CB_HEADER);
        printBoardRow(8);
        printBoardRow(7);
        printBoardRow(6);
        printBoardRow(5);
        printBoardRow(4);
        printBoardRow(3);
        printBoardRow(2);
        printBoardRow(1);
    }

    /**
     * prints a row of this chessboard
     * @param row Row that should be printed
     */
    private void printBoardRow(int row) {
        System.out.print(Strings.DEBUG_INFO + row + " ");
        for (int i = 1; i <= 8; i++) {
            ChessPiece nextPiece = chessPieceAt(i, row);
            if (nextPiece != null) {
                if (nextPiece instanceof Pawn) {
                    if (nextPiece.isWhite()) {
                        System.out.print(Strings.P_UPPERCASE + " ");
                    } else {
                        System.out.print(Strings.P_LOWERCASE + " ");
                    }
                } else if (nextPiece instanceof Rook) {
                    if (nextPiece.isWhite()) {
                        System.out.print(Strings.R_UPPERCASE + " ");
                    } else {
                        System.out.print(Strings.R_LOWERCASE + " ");
                    }
                } else if (nextPiece instanceof Bishop) {
                    if (nextPiece.isWhite()) {
                        System.out.print(Strings.B_UPPERCASE + " ");
                    } else {
                        System.out.print(Strings.B_LOWERCASE + " ");
                    }
                } else if (nextPiece instanceof Knight) {
                    if (nextPiece.isWhite()) {
                        System.out.print(Strings.N_UPPERCASE + " ");
                    } else {
                        System.out.print(Strings.N_LOWERCASE + " ");
                    }
                } else if (nextPiece instanceof Queen) {
                    if (nextPiece.isWhite()) {
                        System.out.print(Strings.Q_UPPERCASE + " ");
                    } else {
                        System.out.print(Strings.Q_LOWERCASE + " ");
                    }
                } else if (nextPiece instanceof King) {
                    if (nextPiece.isWhite()) {
                        System.out.print(Strings.K_UPPERCASE + " ");
                    } else {
                        System.out.print(Strings.K_LOWERCASE + " ");
                    }
                }
            } else {
                char square = 9633;
                System.out.print(square + " ");
            }
        }
        System.out.print("\n");
    }

    /**
     * Moves a ChessPiece on this Board
     * @param positionFrom old Position of the Chesspiece
     * @param positionTo new Position of the Chesspiece
     */
    public void move(Position positionFrom, Position positionTo) {
        Boolean didSpecialMove = false;
        Position from = null;
        Position to = null;
        Position position = null;

        didSpecialMove = castling(positionFrom,positionTo);

        if(didSpecialMove == false) {
            for (Position p : positions) {
                if (p.getX() == positionTo.getX() && p.getY() == positionTo.getY()) {
                    to = p;
                }
            }


            if (to != null) {
                positions.remove(to);
            }

            for (Position p : positions) {
                if (p.getX() == positionFrom.getX() && p.getY() == positionFrom.getY()) {
                    from = p;
                }
            }

            if (from != null) {
                    positions.remove(from);
                    positionFrom.getPiece().move();
                    positions.add(new Position(positionTo.getX(), positionTo.getY(), positionTo.getPiece()));

            }
        }
    }

    private void moveCastling(Position positionFrom, Position positionTo){
        Position from = null;
        Position to = null;
        Position position = null;


            for (Position p : positions) {
                if (p.getX() == positionTo.getX() && p.getY() == positionTo.getY()) {
                    to = p;
                }
            }


            if (to != null) {
                positions.remove(to);
            }

            for (Position p : positions) {
                if (p.getX() == positionFrom.getX() && p.getY() == positionFrom.getY()) {
                    from = p;
                }
            }

            if (from != null) {
                positions.remove(from);
                positionFrom.getPiece().move();
                positions.add(new Position(positionTo.getX(), positionTo.getY(), positionTo.getPiece()));

            }
    }

    public boolean checkforSpecialMoves(Position positionFrom, Position positionTo){
        if(positionFrom.getPiece() instanceof King && positionFrom.getPiece().moved() == false){ //ROCHADE

        }

        return false;
    }

    /**
     * Castling method
     * @param positionFrom old Position
     * @param positionTo new Position
     * @return true if cast was successful
     */
    public boolean castling(Position positionFrom, Position positionTo) {
        boolean back = false;
        if (positionFrom.getPiece().isWhite()) {
            if (positionFrom.equals(Constant.WHITE_CASTLING_LONG_FROM) &&
                    (positionTo.equals(Constant.WHITE_CASTLING_LONG_TO))) {
                positionFrom.getPiece().move();
                moveCastling(positionFrom,Constant.WHITE_CASTLING_LONG_TO);
                moveCastling(Constant.WHITE_CASTLING_LONG_ROOK_FROM,Constant.WHITE_CASTLING_LONG_ROOK_TO);
                IO.sendDebugInfo(Strings.ROCHADE_EQUALS);

                back = true;
            } else if (positionFrom.equals(Constant.WHITE_CASTLING_SHORT_FROM) &&
                    (positionTo.equals(Constant.WHITE_CASTLING_SHORT_TO))) {
                IO.sendDebugInfo(Strings.ROCHADE_EQUALS);
                positionFrom.getPiece().move();
                moveCastling(positionFrom,Constant.WHITE_CASTLING_SHORT_TO);
                moveCastling(Constant.WHITE_CASTLING_SHORT_ROOK_FROM,Constant.WHITE_CASTLING_SHORT_ROOK_TO);
                IO.sendDebugInfo(Strings.ROCHADE_EQUALS);

                back = true;
            }
        }
        else {
            if (positionFrom.equals(Constant.BLACK_CASTLING_LONG_FROM) &&
                    (positionTo.equals(Constant.BLACK_CASTLING_LONG_TO))) {
                positionFrom.getPiece().move();
                moveCastling(positionFrom, Constant.BLACK_CASTLING_LONG_FROM);
                moveCastling(Constant.BLACK_CASTLING_LONG_ROOK_FROM, Constant.BLACK_CASTLING_LONG_ROOK_TO);

                IO.sendDebugInfo(Strings.ROCHADE_EQUALS);
                back = true;
            } else if (positionFrom.equals(Constant.BLACK_CASTLING_SHORT_FROM) &&
                    (positionTo.equals(Constant.BLACK_CASTLING_SHORT_TO))) {
                positionFrom.getPiece().move();
                moveCastling(positionFrom, Constant.BLACK_CASTLING_SHORT_TO);
                moveCastling(Constant.BLACK_CASTLING_SHORT_ROOK_FROM, Constant.BLACK_CASTLING_SHORT_ROOK_TO);
                IO.sendDebugInfo(Strings.ROCHADE_EQUALS);
                back = true;
            }
        }
        return back;
    }

    /**
     * Move method for promotions
     * @param positionFrom old Position
     * @param positionTo new Position
     * @param chessPiece new Chesspiece that the pawn gets promoted to
     */
    public void move(Position positionFrom, Position positionTo, ChessPiece chessPiece) {
        promote(positionFrom, positionTo, chessPiece);
    }

    /**
     * Checks if a King is in danger on this board
     * @param forWhite true if white king should be checked, false if black king should be checked
     * @return true if King is in Danger, false if not
     */
    public boolean isKinginDanger(boolean forWhite) {
        Position kingPosition = this.getKingPosition(forWhite);
        if (kingPosition != null) {
            ArrayList<Position> dangerPositions = DangerChecker.getDangerPositions(this, forWhite);
            for (Position p : dangerPositions) {
                if (p.getX() == kingPosition.getX() && p.getY() == kingPosition.getY()) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    /**
     * Promotes a Pawn
     * @param positionFrom old Position of the Pawn
     * @param positionTo new Position of the Pawn
     * @param chessPiece Chesspiece the Pawn should be turned into
     */
    private void promote(Position positionFrom, Position positionTo, ChessPiece chessPiece) {
        int from = -1;
        int to = -1;

        positionTo.setPiece(chessPiece);
        move(positionFrom, positionTo);

        /**
        Position[] positionArr = (Position[]) positions.toArray(new Position[positions.size()]);
        for (int i = 0; i < positionArr.length; i++) {
            if (positionTo.equals(positionArr[i])) {
                to = i;
            }
        }
        positions.set(to, new Position(positionTo.getX(), positionTo.getY(), chessPiece));
         **/
    }

    public boolean equals(ChessBoard chessBoard){
        boolean found = false;

        if(chessBoard.getPositions().size() != positions.size()){
            return false;
        }

        for(int i = 0; i < chessBoard.getPositions().size(); i++){
            for(int j = 0; j < positions.size(); j++) {
                if (chessBoard.getPositions().get(i).equals(positions.get(j))) {
                    found = true;
                }
            }
            if(!found){
                return false;
            }
            found = false;
        }

        return true;
    }
    /**
     * Adds the standart positions to the Chessboard
     */
    private void setUp() {

        positions.add(new Position(1, 2, new Pawn(true)));

        positions.add(new Position(2, 2, new Pawn(true)));

        positions.add(new Position(3, 2, new Pawn(true)));

        positions.add(new Position(4, 2, new Pawn(true)));

        positions.add(new Position(5, 2, new Pawn(true)));

        positions.add(new Position(6, 2, new Pawn(true)));

        positions.add(new Position(7, 2, new Pawn(true)));

        positions.add(new Position(8, 2, new Pawn(true)));

        positions.add(new Position(1, 1, new Rook(true)));

        positions.add(new Position(2, 1, new Knight(true)));

        positions.add(new Position(3, 1, new Bishop(true)));

        positions.add(new Position(4, 1, new Queen(true)));

        positions.add(new Position(5, 1, new King(true)));

        positions.add(new Position(6, 1, new Bishop(true)));

        positions.add(new Position(7, 1, new Knight(true)));

        positions.add(new Position(8, 1, new Rook(true)));

        positions.add(new Position(1, 7, new Pawn(false)));

        positions.add(new Position(2, 7, new Pawn(false)));

        positions.add(new Position(3, 7, new Pawn(false)));

        positions.add(new Position(4, 7, new Pawn(false)));

        positions.add(new Position(5, 7, new Pawn(false)));

        positions.add(new Position(6, 7, new Pawn(false)));

        positions.add(new Position(7, 7, new Pawn(false)));

        positions.add(new Position(8, 7, new Pawn(false)));

        positions.add(new Position(1, 8, new Rook(false)));

        positions.add(new Position(2, 8, new Knight(false)));

        positions.add(new Position(3, 8, new Bishop(false)));

        positions.add(new Position(4, 8, new Queen(false)));

        positions.add(new Position(5, 8, new King(false)));

        positions.add(new Position(6, 8, new Bishop(false)));

        positions.add(new Position(7, 8, new Knight(false)));

        positions.add(new Position(8, 8, new Rook(false)));

    }


}
