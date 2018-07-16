package Board;

public class Pawn extends ChessPiece {

    public Pawn (boolean white){
        super(white);
    }

    public Pawn (boolean white, boolean moved){
        super(white, Constant.PAWN_VALUE);
        this.setMoved(moved);
    }

    public String toString(){
        if (isWhite()){
            return "white pawn";
        }
        else{
            return "black pawn";
        }
    }
}
