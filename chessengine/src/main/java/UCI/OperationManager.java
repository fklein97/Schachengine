package UCI;

import Board.*;
import MoveEvaluation.*;
import MoveGenerator.*;
import Parameters.Parameters;
import Rating.BoardRater;

import java.util.ArrayList;
import java.util.Random;
/**
 * Class that handles all further task management in the engine after receiving a command
 */
public class OperationManager {

    private final int ASCII_a = 96;
    private final int ASCII_1 = 48;

    private final char QUEEN = 'q';
    private final char ROOK = 'r';
    private final char KNIGHT = 'n';
    private final char PAWN = 'p';
    private final char KING = 'k';
    private final char BISHOP = 'b';

    private final char WQUEEN = 'Q';
    private final char WROOK = 'R';
    private final char WKNIGHT = 'N';
    private final char WPAWN = 'P';
    private final char WKING = 'K';
    private final char WBISHOP = 'B';

    private DataManager history;
    private ChessBoard board;
    private MoveGenerator generator;

    private boolean debug;

    public OperationManager(){

        history = new DataManager();
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

        int charIndex = 0;
        int boardIndex = 1;
        int rowIndex = 8;

        boolean tmpBool = true;

        boolean masterNoCastling = false,castlingBLong = true, castlingBShort = true,
                castlingWShort = true, castlingWLong = true;

        ArrayList<Position> arr = new ArrayList();

        charIndex = fen.indexOf(' ')+1;
        if (fen.charAt(charIndex) == 'w'){
            Parameters.isEngineWhite = true;
        }
        charIndex += 2;
        while (fen.charAt(charIndex) != ' ') {
            switch (fen.charAt(charIndex++))
            {
                case '-': masterNoCastling = true;
                    break;
                case 'K': castlingWShort = false;
                    break;
                case 'Q': castlingWLong = false;
                    break;
                case 'k': castlingBShort = false;
                    break;
                case 'q': castlingBLong = false;
                    break;
                default: break;
            }
        }

        charIndex = 0;

        while (fen.charAt(charIndex) != ' ') {
            switch (fen.charAt(charIndex)) {
                case WPAWN:
                    arr.add(new Position(boardIndex, rowIndex, new Pawn(true)));
                    boardIndex++;
                    break;
                case PAWN:
                    arr.add(new Position(boardIndex, rowIndex, new Pawn(false)));
                    boardIndex++;
                    break;
                case WKNIGHT:
                    arr.add(new Position(boardIndex, rowIndex, new Knight(true)));
                    boardIndex++;
                    break;
                case KNIGHT:
                    arr.add(new Position(boardIndex, rowIndex, new Knight(false)));
                    boardIndex++;
                    break;
                case WBISHOP:
                    arr.add(new Position(boardIndex, rowIndex, new Bishop(true)));
                    boardIndex++;
                    break;
                case BISHOP:
                    arr.add(new Position(boardIndex, rowIndex, new Bishop(false)));
                    boardIndex++;
                    break;
                case WROOK:
                    if (boardIndex == 1 && rowIndex == 1) {
                        tmpBool = castlingWLong;
                    } else if (boardIndex == 8 && rowIndex == 1) {
                        tmpBool = castlingWShort;
                    }
                    arr.add(new Position(boardIndex, rowIndex, new Rook(true, tmpBool )));
                    boardIndex++;
                    tmpBool = true;
                    break;
                case ROOK:
                    if (boardIndex == 1 && rowIndex == 8) {
                        tmpBool = castlingWLong;
                    } else if (boardIndex == 8 && rowIndex == 8) {
                        tmpBool = castlingWShort;
                    }
                    arr.add(new Position(boardIndex, rowIndex, new Rook(false, tmpBool)));
                    boardIndex++;
                    tmpBool = true;
                    break;
                case WQUEEN:
                    arr.add(new Position(boardIndex, rowIndex, new Queen(true)));
                    boardIndex++;
                    break;
                case QUEEN:
                    arr.add(new Position(boardIndex, rowIndex, new Queen(false)));
                    boardIndex++;
                    break;
                case WKING:
                    arr.add(new Position(boardIndex, rowIndex, new King(true,masterNoCastling)));
                    boardIndex++;
                    break;
                case KING:
                    arr.add(new Position(boardIndex, rowIndex, new King(false,masterNoCastling)));
                    boardIndex++;
                    break;
                case '/':
                    rowIndex--;
                    boardIndex = 1;
                    break;
                case '1':
                    boardIndex++;
                    break;
                case '2':
                    boardIndex += 2;
                    break;
                case '3':
                    boardIndex += 3;
                    break;
                case '4':
                    boardIndex += 4;
                    break;
                case '5':
                    boardIndex += 5;
                    break;
                case '6':
                    boardIndex += 6;
                    break;
                case '7':
                    boardIndex += 7;
                    break;
                case '8':
                    boardIndex += 8;
                    break;
                default: break;
            }
        charIndex++;
        }


        board = new ChessBoard(arr);
        board.print();
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

            switch (pieceName.charAt(0)){
                case QUEEN: piece = new Queen(cp.isWhite());
                case ROOK: piece = new Rook(cp.isWhite());
                case KNIGHT: piece = new Knight(cp.isWhite());
                case BISHOP: piece = new Bishop(cp.isWhite());
                default:
                    piece = new Pawn(cp.isWhite());
            }
            oldPos.setPiece(piece);
            newPos.setPiece(piece);
            board.move(oldPos, newPos,piece);
        }
        board.print();
        System.out.println("INFO: CURRENT BOARD RATING: " + BoardRater.getBoardRating(board));
    }

    /**
     * Method go starts the
     */
    public String go(String input){ //TODO unrandomize as soon as the engine knows whats a good move
        /**Random ran = new Random();
        ArrayList<Position> moveSet;                                                            //RANDOMIZED GO METHOD
        Position testPos;
        int randomOne = ran.nextInt(board.getPositions().size());

        boolean kingindanger = false;
        ArrayList<Position> dangerPositions = DangerChecker.getDangerPositions(board,Parameters.isEngineWhite);
        for(Position p : dangerPositions){
            if(p.getX() == board.getKingPosition(Parameters.isEngineWhite).getX() && p.getY() == board.getKingPosition(Parameters.isEngineWhite).getY()){
                kingindanger = true;
                break;
            }
        }

        if(kingindanger == false) {
            while ((board.getPositions().get(randomOne).getPiece().isWhite() != Parameters.isEngineWhite)) {
                randomOne = ran.nextInt(board.getPositions().size());
            }
            moveSet = generator.getMoveSet(board.getPositions().get(randomOne), board);

            //TODO
            if (moveSet.size() <= 0) {
                return go(input);
            }
        }
        else{
            moveSet = generator.getMoveSet(board.getKingPosition(Parameters.isEngineWhite), board);
        }
        int randomTwo = ran.nextInt(moveSet.size());

        String move;

        if(kingindanger == false) {
            move = (posToString(board.getPositions().get(randomOne)) + posToString(moveSet.get(randomTwo)));
            board.getPositions().get(randomOne).getPiece().move();
            board.move(board.getPositions().get(randomOne), moveSet.get(randomTwo));
        }
        else{
            move = (posToString(board.getKingPosition(Parameters.isEngineWhite)) + posToString(moveSet.get(randomTwo)));
            board.getKingPosition(Parameters.isEngineWhite).getPiece().move();
            board.move(board.getKingPosition(Parameters.isEngineWhite), moveSet.get(randomTwo));
        }
        //TODO
         board.print();

         history.addBoard(board);
         return move;
         */

        //boolean kingindanger = board.isKinginDanger(Parameters.isEngineWhite);
        String movestring = "";

        //if(kingindanger == false) {
            Min_Max_Tree tree = new Min_Max_Tree(board,Parameters.isEngineWhite);
            tree.generate_Tree(Parameters.Depth);
            Move move = tree.getBestMove();

            movestring = (posToString(move.getPositionFrom()) + (posToString(move.getPositionTo())));
            board.move(move.getPositionFrom(), move.getPositionTo());
        //}
        /**else{                                                                      //OLD KING DANGER CHECK
            ArrayList<Position> kingmoveset = new ArrayList<>();
            kingmoveset = MoveGenerator.getMoveSet(board.getKingPosition(Parameters.isEngineWhite),board);
            Random rng = new Random();
            int randomkingmove = rng.nextInt(kingmoveset.size());
            Position newPos = kingmoveset.get(randomkingmove);

            movestring = (posToString(board.getKingPosition(Parameters.isEngineWhite)))+ posToString(newPos);
            board.move(board.getKingPosition(Parameters.isEngineWhite),newPos);
        }*/
        board.print();
        System.out.println("INFO: CURRENT BOARD RATING: " + BoardRater.getBoardRating(board));
        System.gc();
        return movestring;
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
