package Board;

public class Bishop extends ChessPiece {

    public Bishop(boolean white){

        super(white, Constant.BISHOP_VALUE);
    }

    public String toString(){
        if(isWhite()){
            return "white bishop";
        }
        else{
            return "black bishop";
        }
    }
}
