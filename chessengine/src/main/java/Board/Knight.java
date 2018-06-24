package Board;

public class Knight extends ChessPiece{

    public Knight(boolean white){

        super(white, Constant.KNIGHT_VALUE);
    }

    public String toString(){
        if (isWhite()){
            return "white knight";
        }
        else{
            return "black knight";
        }
    }
}
