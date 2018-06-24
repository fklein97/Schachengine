package Board;

public class Queen extends ChessPiece {

    public Queen(boolean white){

        super(white, Constant.QUEEN_VALUE);
    }

    public String toString(){
        if (isWhite()){
            return "white queen";
        }
        else{
            return "black queen";
        }
    }
}
