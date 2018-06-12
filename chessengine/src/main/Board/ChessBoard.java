public class ChessBoard {

    private ChessPiece[]    chessPieces ;
    private Position[]      positions;


            //-----Getter Setter--------
    public ChessPiece[] getChessPieces() {
        return chessPieces;
    }

    public void setChessPieces(ChessPiece[] chessPieces) {
        this.chessPieces = chessPieces;
    }

    public Position[] getPositions() {
        return positions;
    }

    public void setPositions(Position[] positions) {
        this.positions = positions;
    }

    public ChessBoard(){
        chessPieces = new ChessPiece[Constant.CHESS_PIECES];
        positions   = new Position[Constant.CHESS_PIECES];

        setUp();
    }

        //--------Constructor-----------
    public void newGame(){
        setUp();
    }

    public void newGame(ChessPiece[] chessPieces, Position[] positions){
        this.chessPieces    = chessPieces;
        this.positions      = positions;
    }

    public void move(Position positionFrom, Position positionTo){
        for(int i =0; i < Constant.CHESS_PIECES; i++){
            if(positions[i].equals(positionFrom)){
                for(int j =0; j < Constant.CHESS_PIECES; j++){
                    if(positions[j].equals(positionTo)){
                        positions[i]    = positionTo;
                        positions[j]    = new Position(0,0);
                        chessPieces[j]  = new ChessPiece(true);
                    }
                }
            }
        }
    }

    private void setUp(){

        //white
        chessPieces[0]  = new Pawn(true);
        positions[0]     = new Position(1,2);

        chessPieces[1]  = new Pawn(true);
        positions[1]     = new Position(2,2);

        chessPieces[2]  = new Pawn(true);
        positions[2]     = new Position(3,2);

        chessPieces[3]  = new Pawn(true);
        positions[3]     = new Position(4,2);

        chessPieces[4]  = new Pawn(true);
        positions[4]     = new Position(5,2);

        chessPieces[5]  = new Pawn(true);
        positions[5]     = new Position(6,2);

        chessPieces[6]  = new Pawn(true);
        positions[6]     = new Position(7,2);

        chessPieces[7]  = new Pawn(true);
        positions[7]     = new Position(8,2);

        chessPieces[8]  = new Rook(true);
        positions[8]     = new Position(1,1);

        chessPieces[0]  = new Knight(true);
        positions[0]     = new Position(2,1);

        chessPieces[0]  = new Bishop(true);
        positions[0]     = new Position(3,1);

        chessPieces[0]  = new King(true);
        positions[0]     = new Position(4,1);

        chessPieces[0]  = new Queen(true);
        positions[0]     = new Position(5,1);

        chessPieces[0]  = new Bishop(true);
        positions[0]     = new Position(6,1);

        chessPieces[0]  = new Knight(true);
        positions[0]     = new Position(7,1);

        chessPieces[0]  = new Rook(true);
        positions[0]     = new Position(8,1);

        //black
        chessPieces[0]  = new Pawn(false);
        positions[0]     = new Position(1,7);

        chessPieces[1]  = new Pawn(false);
        positions[1]     = new Position(2,7);

        chessPieces[2]  = new Pawn(false);
        positions[2]     = new Position(3,7);

        chessPieces[3]  = new Pawn(false);
        positions[3]     = new Position(4,7);

        chessPieces[4]  = new Pawn(false);
        positions[4]     = new Position(5,7);

        chessPieces[5]  = new Pawn(false);
        positions[5]     = new Position(6,7);

        chessPieces[6]  = new Pawn(false);
        positions[6]     = new Position(7,7);

        chessPieces[7]  = new Pawn(false);
        positions[7]     = new Position(8,7);

        chessPieces[8]  = new Rook(false);
        positions[8]     = new Position(1,8);

        chessPieces[0]  = new Knight(false);
        positions[0]     = new Position(2,8);

        chessPieces[0]  = new Bishop(false);
        positions[0]     = new Position(3,8);

        chessPieces[0]  = new King(false);
        positions[0]     = new Position(4,8);

        chessPieces[0]  = new Queen(false);
        positions[0]     = new Position(5,8);

        chessPieces[0]  = new Bishop(false);
        positions[0]     = new Position(6,8);

        chessPieces[0]  = new Knight(false);
        positions[0]     = new Position(7,8);

        chessPieces[0]  = new Rook(false);
        positions[0]     = new Position(8,8);

    }

}
