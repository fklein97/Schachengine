
public class Position {

    private int x;
    private int y;

    private ChessPiece piece;

    public Position(int x, int y, ChessPiece piece){
        if(x < 1 || x > 8){
            throw new RuntimeException(Constant.WRONG_POSITION);
        }
        if(y < 1 || y > 8){
            throw new RuntimeException(Constant.WRONG_POSITION);
        }
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }
}
