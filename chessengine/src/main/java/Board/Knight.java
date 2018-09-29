package Board;

import Parameters.Strings;

public class Knight extends ChessPiece{

    public Knight(boolean white){

        super(white, Constant.KNIGHT_VALUE);
    }

    public String toString(){
        if (isWhite()){
            return Strings.WHITE_KNIGHT;
        }
        else{
            return Strings.BLACK_KNIGHT;
        }
    }
}
