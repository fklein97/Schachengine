import Board.*;
import MoveGenerator.*;

import java.util.ArrayList;
import java.util.Random;
/**
 * Class that handles all further task management in the engine after receiving a command
 */
public class OperationManager {

    private final int ASCII_a = 96;
    private final int ASCII_1 = 48;

    private final String QUEEN = "q";
    private final String ROOK = "r";
    private final String KNIGHT = "k";
    private final String BISHOP = "b";

    private ChessBoard board;
    private MoveGenerator generator;

    private boolean debug;

    public OperationManager(){

        board = new ChessBoard();
        generator = new MoveGenerator();
    }

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

        ChessPiece cp = board.chessPieceAt(oldX,oldY);
        Position oldPos = new Position(oldX,oldY,cp);
        Position newPos = new Position(x,y,cp);

        if (moveString.length() == 4) {

            board.move(oldPos, newPos);

        } else {

            String pieceName = moveString.substring(4);
            ChessPiece piece;

            switch (pieceName){
                case QUEEN: piece = new Queen(cp.isWhite());
                case ROOK: piece = new Rook(cp.isWhite());
                case KNIGHT: piece = new Knight(cp.isWhite());
                case BISHOP: piece = new Bishop(cp.isWhite());
                default:
                    piece = new Pawn(cp.isWhite());
            }
            oldPos.setPiece(piece);
            newPos.setPiece(piece);
            board.promote(oldPos, newPos,piece);
        }
    }

    /**
     * Method go starts the
     */
    public String go(String input){
        Random ran = new Random();
        ArrayList<Position> moveSet;
        Position testPos;
        int randomOne = ran.nextInt(board.getPositions().size());
        while(board.getPositions().get(randomOne).getPiece().isWhite()){
            randomOne = ran.nextInt(board.getPositions().size());
        }
        moveSet = generator.getMoveSet(board.getPositions().get(randomOne),board);

        //TODO
        if (moveSet.isEmpty()){
            return go(input);
        }

        int randomTwo = ran.nextInt(moveSet.size());

         String move = (posToString(board.getPositions().get(randomOne))+posToString(moveSet.get(randomTwo)));
         board.getPositions().get(randomOne).getPiece().move();
         board.move(board.getPositions().get(randomOne),moveSet.get(randomTwo));

         return move;
    }

    /**
     * Method to convert positions to Strings
     * @param pos Input position
     * @return String of position
     */
    public String posToString(Position pos){

       int x = pos.getX();
       int y = pos.getY();

       x += ASCII_a;
       y += ASCII_1;

       return new String(Character.toString((char)x)+Character.toString((char)y));

    }
}
