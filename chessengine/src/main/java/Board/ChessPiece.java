package Board;

public class ChessPiece {
    private int     value;
    private boolean white;
    private boolean moved;

    public ChessPiece(boolean white, int value){
        this.value  = value;
        this.white  = white;
        this.moved  = false;
    }

    public ChessPiece(boolean white){
        this.value = Constant.KING_VALUE;
        this.white = white;
        this.moved = false;
    }
    public void move(){
        moved = true;
    }

    public boolean isWhite(){
        return white;
    }

    public boolean moved(){
        return moved;
    }

    public void setMoved(boolean moved) { this.moved = moved;}

    public String toString(){
        if(isWhite()){
            return "white chesspiece";
        }
        else{
            return "black chesspiece";
        }
    }

}
