package Board;

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
    public ChessPiece getPieceCopy() {
        if(this.piece instanceof King){
            return new King(piece.isWhite(), piece.moved());
        }
        else if(this.piece instanceof Queen){
            return new Queen(piece.isWhite());
        }
        else if(this.piece instanceof Rook){
            return new Rook(piece.isWhite(), piece.moved());
        }
        else if(this.piece instanceof Bishop){
            return new Bishop(piece.isWhite());
        }
        else if(this.piece instanceof Knight){
            return new Knight(piece.isWhite());
        }
        else if(this.piece instanceof Pawn){
            return new Pawn(piece.isWhite(),piece.moved());
        }
        else{
            return new Pawn(piece.isWhite(),piece.moved());
        }
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public boolean equals(Position position){
        if(this.x == position.getX() && this.y == position.getY() && this.piece.equals(position.getPiece())){
            return true;
        }
        return false;
    }

}
