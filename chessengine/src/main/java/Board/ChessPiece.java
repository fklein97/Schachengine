package Board;

/**
 * @author Moritz Grill
 */
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
     * ChessPiece Constructor
     * @param white color of the ChessPiece (true = white; false = black)
     * @param moved tells if the ChessPiece has already moved or not
     * @param value ChessPiece Value
     */
    public ChessPiece(boolean white, boolean moved, int value){
        this.white = white;
        this.moved = moved;
        this.value = value;
    }

    /**
     * ChessPiece Constructor
     * @param white color of the ChessPiece (true = white; false = black)
     */
    public ChessPiece(boolean white){
        this.value = Constant.KING_VALUE;
        this.white = white;
        this.moved = false;
    }

    /**
     * Sets the move-bool-value to true
     */
    public void move(){
        moved = true;
    }

    /**
     * Returns if the ChessPiece is white or black
     * @return true, if white, false, if black
     */
    public boolean isWhite(){
        return white;
    }

    /**
     * Returns the value of a the ChessPiece
     * @return value of the ChessPiece
     */
    public int getValue(){ return value; }

    /**
     * Returns if the ChessPiece has already been moved
     * @return true if moved, false if not
     */
    public boolean moved(){
        return moved;
    }

    /**
     * moved-Setter
     * @param moved
     */
    public void setMoved(boolean moved) { this.moved = moved;}

    /**
     * Returns if a given ChessPiece equals this ChessPiece
     * @param cp the given ChessPiece
     * @return true, if equal, false, if not
     */
    public boolean equals(ChessPiece cp){
        return (cp.moved() == this.moved() && cp.isWhite() == this.isWhite() && cp.getValue() == this.getValue());
    }

    /**
     * Generates an info-String of the ChessPiece
     * @return info-String
     */
    public String toString(){
        if(isWhite()){
            return "white chesspiece";
        }
        else{
            return "black chesspiece";
        }
    }

}
