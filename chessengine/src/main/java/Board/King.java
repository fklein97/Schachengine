package Board;

public class King extends ChessPiece{

    public King(boolean white, boolean moved){

        super (white, Constant.KING_VALUE);
        this.setMoved(moved);
    }


    public King(boolean white){
        super(white, Constant.KING_VALUE);

    }

    public String toString(){
        if (isWhite()){
            return "white king";
        }
        else{
            return "black king";
        }
    }
}
