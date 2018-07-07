package Board;

import java.util.*;


public class ChessBoard {

    private final static int NULL = 0;

    private ArrayList<Position> positions;


    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }


    public ChessBoard(){
        positions   = new ArrayList<Position>();

        setUp();
    }
    public ChessBoard(ArrayList<Position> positions){
        this.positions = positions;
    }

    public Position getKingPosition(boolean forWhite){
        for(Position p : positions){
            if(p.getPiece().isWhite() == forWhite && p.getPiece() instanceof King){
                return p;
            }
        }

        return null;
    }

    public boolean positionIsTaken(int x, int y){
        ChessPiece chessPiece = chessPieceAt(x,y );
        if(chessPiece != null){
            return true;
        }
        return false;
    }

    public ChessPiece chessPieceAt(int xCoordinate, int yCordinate){
        Position[] positionsArr = new Position[positions.size()];
        positionsArr = (Position[]) positions.toArray(positionsArr);

        for(int i = 0; i < positionsArr.length; i++){
            if(positionsArr[i].getX() == xCoordinate && positionsArr[i].getY() == yCordinate){
                return positionsArr[i].getPiece();
            }
        }

        return null;
    }

    public void newGame(){
        setUp();
    }

    public void newGame(ArrayList<Position> positions){
        this.positions      = positions;
    }

    public void print(){
        System.out.print("INFO: 0 1 2 3 4 5 6 7 8\n");
        printBoardRow(8);
        printBoardRow(7);
        printBoardRow(6);
        printBoardRow(5);
        printBoardRow(4);
        printBoardRow(3);
        printBoardRow(2);
        printBoardRow(1);
        System.out.print("\n");
    }

    private void printBoardRow(int row){
        System.out.print("INFO: " + row + " ");
        for(int i = 1; i <= 8; i++){
            ChessPiece nextPiece = chessPieceAt(i,row);
            if(nextPiece != null) {
                if (nextPiece instanceof Pawn) {
                    if (nextPiece.isWhite()) {
                        System.out.print("P ");
                    } else {
                        System.out.print("p ");
                    }
                }
                else if(nextPiece instanceof Rook){
                    if(nextPiece.isWhite()){
                        System.out.print("R ");
                    }
                    else{
                        System.out.print("r ");
                    }
                }
                else if(nextPiece instanceof Bishop){
                    if(nextPiece.isWhite()){
                        System.out.print("B ");
                    }
                    else{
                        System.out.print("b ");
                    }
                }
                else if(nextPiece instanceof Knight){
                    if(nextPiece.isWhite()){
                        System.out.print("N ");
                    }
                    else{
                        System.out.print("n ");
                    }
                }
                else if(nextPiece instanceof Queen){
                    if(nextPiece.isWhite()){
                        System.out.print("Q ");
                    }
                    else{
                        System.out.print("q ");
                    }
                }
                else if(nextPiece instanceof King){
                    if(nextPiece.isWhite()){
                        System.out.print("K ");
                    }
                    else{
                        System.out.print("k ");
                    }
                }
            }
            else{
                char square = 9633;
                System.out.print(square+" ");
            }
        }
        System.out.print("\n");
    }

   /** public void move(Position positionFrom, Position positionTo){
        int from = -1;
        int to = -1;
        for(Position p : positions){
            if(p.getX() == positionFrom.getX() && p.getY() == positionFrom.getY()){
                from = positions.indexOf(p);
            }
            if(p.getX() == positionTo.getX() && p.getY() == positionTo.getY()){
                to = positions.indexOf(p);
            }
        }

        if(from != -1){
            positions.set(from, new Position(positionTo.getX(),positionTo.getY(),positionFrom.getPiece()));
        }
        if(to != -1){
            positions.remove(to);
        }
    }*/

    public void move(Position positionFrom, Position positionTo){
        int from    = -1;
        int to      = -1;
        Position[] positionArr =  (Position[]) positions.toArray(new Position[positions.size()]);
        for(int i = 0; i < positionArr.length; i++){
            if(positionFrom.equals(positionArr[i])){
                from = i;
            }
            if(positionTo.equals(positionArr[i])){
                to = i;
            }
        }
        if(from >= NULL){
            positions.set(from, new Position(positionTo.getX(),positionTo.getY(), positionFrom.getPiece()));
        }
        if(to >= NULL){
            positions.remove(to);
        }

    }

    public void move(Position positionFrom, Position positionTo, ChessPiece chessPiece){
        promote(positionFrom, positionTo, chessPiece);
    }


    public void promote(Position positionFrom, Position positionTo, ChessPiece chessPiece){
        int from    = -1;
        int to      = -1;
        move(positionFrom, positionTo);
        Position[] positionArr =  (Position[]) positions.toArray(new Position[positions.size()]);
        for(int i = 0; i < positionArr.length; i++){
            if(positionTo.equals(positionArr[i])){
                to = i;
            }
        }
        positions.set(to, new Position(positionTo.getX(), positionTo.getY(), chessPiece));
    }



    private void setUp(){

        positions.add(new Position(1,2, new Pawn(true)));

        positions.add(new Position(2,2, new Pawn(true)));

        positions.add(new Position(3,2, new Pawn(true)));

        positions.add(new Position(4,2, new Pawn(true)));

        positions.add(new Position(5,2, new Pawn(true)));

        positions.add(new Position(6,2, new Pawn(true)));

        positions.add(new Position(7,2, new Pawn(true)));

        positions.add(new Position(8,2, new Pawn(true)));

        positions.add(new Position(1,1, new Rook(true)));

        positions.add(new Position(2,1, new Knight(true)));

        positions.add(new Position(3,1, new Bishop(true)));

        positions.add(new Position(4,1, new King(true)));

        positions.add(new Position(5,1, new Queen(true)));

        positions.add(new Position(6,1, new Bishop(true)));

        positions.add(new Position(7,1, new Knight(true)));

        positions.add(new Position(8,1, new Rook(true)));

        positions.add(new Position(1,7, new Pawn(false)));

        positions.add(new Position(2,7, new Pawn(false)));

        positions.add(new Position(3,7, new Pawn(false)));

        positions.add(new Position(4,7, new Pawn(false)));

        positions.add(new Position(5,7, new Pawn(false)));

        positions.add(new Position(6,7, new Pawn(false)));

        positions.add(new Position(7,7, new Pawn(false)));

        positions.add(new Position(8,7, new Pawn(false)));

        positions.add(new Position(1,8, new Rook(false)));

        positions.add(new Position(2,8, new Knight(false)));

        positions.add(new Position(3,8, new Bishop(false)));

        positions.add(new Position(4,8, new Queen(false)));

        positions.add(new Position(5,8, new King(false)));

        positions.add(new Position(6,8, new Bishop(false)));

        positions.add(new Position(7,8, new Knight(false)));

        positions.add(new Position(8,8, new Rook(false)));

    }



}
