package Board;

public class Move {

    private Position positionFrom;
    private Position positionTo;

    public Move(Position positionFrom,Position positionTo ){
        this.positionFrom   = positionFrom;
        this.positionTo     = positionTo;
    }

    public Move(){
        this(null, null);
    }

    public Position getPositionFrom() {
        return positionFrom;
    }

    public Position getPositionTo() {
        return positionTo;
    }

    public boolean equals(Object o){
        Move m = (Move) o;
        if(m.getPositionFrom().getPiece().toString().equals(this.getPositionFrom().getPiece().toString())
                && m.getPositionTo().getPiece().toString().equals(this.getPositionTo().getPiece().toString())
                && m.getPositionFrom().getX() == this.getPositionFrom().getX()
                && m.getPositionFrom().getY() == this.getPositionFrom().getY()
                && m.getPositionTo().getX() == this.getPositionTo().getX()
                && m.getPositionTo().getY() == this.getPositionTo().getY()){
            return true;
        }
        else {
            return false;
        }
    }
}
