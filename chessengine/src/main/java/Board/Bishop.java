package Board;

import Parameters.Strings;

/**
 * Class for Bishop-Objects
 */
public class Bishop extends ChessPiece {

    public Bishop(boolean white){

        super(white, Constant.BISHOP_VALUE);
    }

    /**
     * Overrides Object-toString()
     * @return String describing this Bishop
     */
    public String toString(){
        if(isWhite()){
            return Strings.WHITE_BISHOP;
        }
        else{
            return Strings.BLACK_BISHOP;
        }
    }
}
