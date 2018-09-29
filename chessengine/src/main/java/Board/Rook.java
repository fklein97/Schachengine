package Board;

import Parameters.Strings;

public class Rook extends ChessPiece {

    public Rook(boolean white){

        super (white, Constant.ROOK_VALUE);
    }

    public Rook(boolean white, boolean moved){

        super (white, Constant.ROOK_VALUE);
        this.setMoved(moved);
    }

    public String toString(){
        if (isWhite()){
            return Strings.WHITE_ROOK;
        }
        else{
            return Strings.BLACK_ROOK;
        }
    }
}
