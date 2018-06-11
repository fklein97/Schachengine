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



    public void newGame(){
        setUp();
    }

    public void newGame(ArrayList<Position> positions){
        this.positions      = positions;
    }


    public void move(Position positionFrom, Position positionTo){
        int from    = positions.indexOf(positionFrom);
        int to      = positions.indexOf(positionTo);
        positions.get(from).setX(positionTo.getX());
        positions.get(from).setY(positionTo.getY());
        if(to >= NULL){
            positions.remove(to);
        }

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

        positions.add(new Position(4,8, new King(false)));

        positions.add(new Position(5,8, new Queen(false)));

        positions.add(new Position(6,8, new Bishop(false)));

        positions.add(new Position(7,8, new Knight(false)));

        positions.add(new Position(8,8, new Rook(false)));

    }



}
