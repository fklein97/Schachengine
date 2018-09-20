package Board;

import MoveGenerator.DangerChecker;
import UCI.IO;

import java.util.*;


public class ChessBoard {

    private final static int NULL = 0;

    private ArrayList<Position> positions;


    public ArrayList<Position> getPositions(){
        return positions;
    }

    public ArrayList<Position> getPositionsCopy() {
        ArrayList<Position> pos = new ArrayList<Position>();
        for(Position p : positions){
            pos.add(new Position(p.getX(),p.getY(),p.getPieceCopy()));
        }

        return pos;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }


    public ChessBoard() {
        positions = new ArrayList<Position>();

        setUp();
    }

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

    public boolean positionIsTaken(int x, int y) {
        ChessPiece chessPiece = chessPieceAt(x, y);
        if (chessPiece != null) {
            return true;
        }
        return false;
    }

    public ChessPiece chessPieceAt(int xCoordinate, int yCoordinate) {
        for(Position p : positions){
            if(p.getX() == xCoordinate && p.getY() == yCoordinate){
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

    public void print() {
        System.out.print("DEBUG INFO: 0 1 2 3 4 5 6 7 8\n");
        printBoardRow(8);
        printBoardRow(7);
        printBoardRow(6);
        printBoardRow(5);
        printBoardRow(4);
        printBoardRow(3);
        printBoardRow(2);
        printBoardRow(1);
    }

    private void printBoardRow(int row) {
        System.out.print("DEBUG INFO: " + row + " ");
        for (int i = 1; i <= 8; i++) {
            ChessPiece nextPiece = chessPieceAt(i, row);
            if (nextPiece != null) {
                if (nextPiece instanceof Pawn) {
                    if (nextPiece.isWhite()) {
                        System.out.print("P ");
                    } else {
                        System.out.print("p ");
                    }
                } else if (nextPiece instanceof Rook) {
                    if (nextPiece.isWhite()) {
                        System.out.print("R ");
                    } else {
                        System.out.print("r ");
                    }
                } else if (nextPiece instanceof Bishop) {
                    if (nextPiece.isWhite()) {
                        System.out.print("B ");
                    } else {
                        System.out.print("b ");
                    }
                } else if (nextPiece instanceof Knight) {
                    if (nextPiece.isWhite()) {
                        System.out.print("N ");
                    } else {
                        System.out.print("n ");
                    }
                } else if (nextPiece instanceof Queen) {
                    if (nextPiece.isWhite()) {
                        System.out.print("Q ");
                    } else {
                        System.out.print("q ");
                    }
                } else if (nextPiece instanceof King) {
                    if (nextPiece.isWhite()) {
                        System.out.print("K ");
                    } else {
                        System.out.print("k ");
                    }
                }
            } else {
                char square = 9633;
                System.out.print(square + " ");
            }
        }
        System.out.print("\n");
    }

    public void move(Position positionFrom, Position positionTo) {
        Boolean didSpecialMove = false;
        Position from = null;
        Position to = null;

        didSpecialMove = castling(positionFrom,positionTo);
     //   System.out.println("INFO DEBUG: " + didSpecialMove.toString());

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

    public boolean checkforSpecialMoves(Position positionFrom, Position positionTo){
        if(positionFrom.getPiece() instanceof King && positionFrom.getPiece().moved() == false){ //ROCHADE

        }

        return false;
    }

    /**
     * Castling method
     * @param positionFrom
     * @param positionTo
     * @return false if cast was successful
     */
    public boolean castling(Position positionFrom, Position positionTo) {
        boolean back = false;
        if (positionFrom.getPiece().isWhite()) {
            if (positionFrom.equals(Constant.WHITE_CASTLING_LONG_FROM) &&
                    (positionTo.equals(Constant.WHITE_CASTLING_LONG_TO))) {
                positionFrom.getPiece().move();
                move(positionFrom,Constant.WHITE_CASTLING_LONG_TO);
                move(Constant.WHITE_CASTLING_LONG_ROOK_FROM,Constant.WHITE_CASTLING_LONG_ROOK_TO);
                IO.sendDebugInfo("Rochade + found equals");
                back = true;
            } else if (positionFrom.equals(Constant.WHITE_CASTLING_SHORT_FROM) &&
                    (positionTo.equals(Constant.WHITE_CASTLING_SHORT_TO))) {
                IO.sendDebugInfo("Rochade + found equals");
                positionFrom.getPiece().move();
                move(positionFrom,Constant.WHITE_CASTLING_SHORT_TO);
                move(Constant.WHITE_CASTLING_SHORT_ROOK_FROM,Constant.WHITE_CASTLING_SHORT_ROOK_TO);
                IO.sendDebugInfo("Rochade + found equals");
                back = true;
            }
        }
        else {
            if (positionFrom.equals(Constant.BLACK_CASTLING_LONG_FROM) &&
                    (positionTo.equals(Constant.BLACK_CASTLING_LONG_TO))) {
                positionFrom.getPiece().move();
                move(positionFrom, Constant.BLACK_CASTLING_LONG_FROM);
                move(Constant.BLACK_CASTLING_LONG_ROOK_FROM, Constant.BLACK_CASTLING_LONG_ROOK_TO);
                IO.sendDebugInfo("Rochade + found equals");
                back = true;
            } else if (positionFrom.equals(Constant.BLACK_CASTLING_SHORT_FROM) &&
                    (positionTo.equals(Constant.BLACK_CASTLING_SHORT_TO))) {
                positionFrom.getPiece().move();
                move(positionFrom, Constant.BLACK_CASTLING_SHORT_TO);
                move(Constant.BLACK_CASTLING_SHORT_ROOK_FROM, Constant.BLACK_CASTLING_SHORT_ROOK_TO);
                IO.sendDebugInfo("Rochade + found equals");
                back = true;
            }
        }
        return back;
    }


    /**
     * public void move(Position positionFrom, Position positionTo){ //moritz move method
     * int from    = -1;
     * int to      = -1;
     * Position[] positionArr =  (Position[]) positions.toArray(new Position[positions.size()]);
     * for(int i = 0; i < positionArr.length; i++){
     * if(positionFrom.equals(positionArr[i])){
     * from = i;
     * }
     * if(positionTo.equals(positionArr[i])){
     * to = i;
     * }
     * }
     * if(from >= 0){
     * positions.set(from, new Position(positionTo.getX(),positionTo.getY(), positionFrom.getPiece()));
     * positions.get(from).getPiece().move();
     * }
     * if(to >= 0){
     * positions.remove(to);
     * }
     * <p>
     * }
     */

    public void move(Position positionFrom, Position positionTo, ChessPiece chessPiece) {
        promote(positionFrom, positionTo, chessPiece);
    }

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
