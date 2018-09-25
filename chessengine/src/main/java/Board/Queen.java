package Board;

import Parameters.Strings;

public class Queen extends ChessPiece {

    public Queen(boolean white){

        super(white, Constant.QUEEN_VALUE);
    }

    public String toString(){
        if (isWhite()){
            return Strings.WHITE_QUEEN;
        }
        else{
            return Strings.BLACK_QUEEN;
        }
    }
}
