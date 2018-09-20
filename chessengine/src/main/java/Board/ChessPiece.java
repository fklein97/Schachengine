package Board;

public class ChessPiece {
    private int     value;
    private boolean white;
    private boolean moved;

    /**
     * ChessPiece Constructor
     * @param white color of the ChessPiece (true = white; false = black)
     * @param value ChessPiece Value
     */
    public ChessPiece(boolean white, int value){
        this.value  = value;
        this.white  = white;
        this.moved  = false;
    }

    /**
     *
     * @param white
     * @param moved
     * @param value
     */
    public ChessPiece(boolean white, boolean moved, int value){
        this.white = white;
        this.moved = moved;
        this.value = value;
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

    public int getValue(){ return value; }

    public boolean moved(){
        return moved;
    }

    public void setMoved(boolean moved) { this.moved = moved;}

    public boolean equals(ChessPiece cp){
        return (cp.moved() == this.moved() && cp.isWhite() == this.isWhite() && cp.getValue() == this.getValue());
    }

    public String toString(){
        if(isWhite()){
            return "white chesspiece";
        }
        else{
            return "black chesspiece";
        }
    }

}
