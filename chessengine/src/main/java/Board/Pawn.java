package Board;

public class Pawn extends ChessPiece {

    public Pawn (boolean white){

        super(white, Constant.PAWN_VALUE);
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
