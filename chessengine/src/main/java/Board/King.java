package Board;

public class King extends ChessPiece{

    /**
     * Constructor
     * @param white color, true = white , false = black
     * @param moved has moved?
     */
    public King(boolean white, boolean moved){
        super (white, Constant.KING_VALUE);
        this.setMoved(moved);
    }


    /**
     * Constructor
     * @param white color, true = white, false = black
     */
    public King(boolean white){
        super(white, Constant.KING_VALUE);

    }

    /**
     * creates info-string
     * @return info-string
     */
    public String toString(){
        if (isWhite()){
            return "white king";
        }
        else{
            return "black king";
        }
    }
}
