import Board.*;
/**
 * Class that handles all further task management in the engine after receiving a command
 */
public class OperationManager {

    private final int ASCII_a = 96;
    private final int ASCII_1 = 47;

    private final String QUEEN = "q";
    private final String ROOK = "r";
    private final String KNIGHT = "k";
    private final String BISHOP = "b";

    private ChessBoard board;
    private boolean debug;

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isDebug() {
        return debug;
    }

    /**
     *  Method to handle Fenstring inputs
     * @param fen Fenstring
     */
    public void handleFEN(String fen){

    }

    /**
     * Method to create positions from move strings
     * @param moveString Last player move as String (e.g. e3e4)
     */
    public void playerMove(String moveString){

        int oldX = moveString.charAt(0) - ASCII_a;
        int oldY = moveString.charAt(1) - ASCII_1;
        int x = moveString.charAt(2) - ASCII_a;
        int y = moveString.charAt(3) - ASCII_1;

        Position oldPos = new Position(oldX,oldY);
        Position newPos = new Position(x,y);

        if (moveString.length() == 5) {

            board.move(oldPos, newPos);

        } else {

            String pieceName = moveString.substring(4);
            ChessPiece piece;

            switch (pieceName){
                case QUEEN: piece = new Queen(true);
                case ROOK: piece = new Rook(true);
                case KNIGHT: piece = new Knight(true);
                case BISHOP: piece = new Bishop(true);
            }
         //   board. PROMOTION MOVE (piece, positionold, positionnew);
        }
    }

    //TODO Implementieren
    /**
     * Method go starts the
     */
    public String go(String input){
        return new String("");
    }

}
