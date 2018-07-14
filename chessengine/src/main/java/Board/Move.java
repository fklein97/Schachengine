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
}
