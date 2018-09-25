package UCI;

import Board.*;
import MoveEvaluation.*;
import MoveGenerator.*;
import Parameters.Parameters;
import Rating.BoardRater;

import java.util.ArrayList;

/**
 * Class that handles all further task management in the engine after receiving a command
 * @author Dominic Büch, Florian Klein, Moritz Grill
 */
public class OperationManager {

    private static final int ASCII_a = 96;
    private static final int ASCII_1 = 48;

    private static final char QUEEN = 'q';
    private static final char ROOK = 'r';
    private static final char KNIGHT = 'n';
    private static final char PAWN = 'p';
    private static final char KING = 'k';
    private static final char BISHOP = 'b';

    private static final char WQUEEN = 'Q';
    private static final char WROOK = 'R';
    private static final char WKNIGHT = 'N';
    private static final char WPAWN = 'P';
    private static final char WKING = 'K';
    private static final char WBISHOP = 'B';

    private DataManager history;
    private ChessBoard board;
    private MoveGenerator generator;
    private TimeManager timer;

    private boolean debug;

    /**
     * Constructor
     */
    public OperationManager(){

        history = new DataManager();
        board = new ChessBoard();
        generator = new MoveGenerator();
        timer = new TimeManager();
    }

    /**
     * Returns the timemanager
     * @return
     */
    public TimeManager getTimer(){ return this.timer; }


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

        if(fen.contains("w")){
            Parameters.isEngineWhite = true;
            Parameters.isColorSet = true;
        }
        else{
            Parameters.isEngineWhite = false;
            Parameters.isColorSet = true;
        }

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
                case QUEEN: piece = new Queen(cp.isWhite()); break;
                case ROOK: piece = new Rook(cp.isWhite()); break;
                case KNIGHT: piece = new Knight(cp.isWhite()); break;
                case BISHOP: piece = new Bishop(cp.isWhite()); break;
                default:
                    piece = new Pawn(cp.isWhite());
            }
            board.move(oldPos, newPos,piece);
        }
        board.print();
        IO.sendDebugInfo("CURRENT BOARD RATING: " + BoardRater.getBoardRating(board));
    }

    /**
     * Starts engine calculating
     * @param input detailed go input (not used anymore)
     * @return best move as string
     */
    public String go(String input){

        MinMaxTree chessicasBrain = new MinMaxTree();
        Move move = chessicasBrain.initialize(new ChessBoard(board.getPositionsCopy()), Parameters.isEngineWhite);

        String movestring = "";

        movestring = (posToString(move.getPositionFrom()) + (posToString(move.getPositionTo())));
        board.move(move.getPositionFrom(), move.getPositionTo());

        board.print();
        int boardrating = BoardRater.getBoardRating(board);
        IO.sendDebugInfo("CURRENT BOARD RATING: " + boardrating);
        if(Parameters.isEngineWhite) {
            IO.answer("info score cp " + boardrating/10);
        }
        else{
            IO.answer("info score cp " + boardrating/-10);
        }
        System.gc();
        return movestring;

    }

    /**
     * Method to convert positions to Strings
     * @param pos Input position
     * @return String of position
     */
    public static String posToString(Position pos){

       int x = pos.getX();
       int y = pos.getY();

       x += ASCII_a;
       y += ASCII_1;

       return new String(Character.toString((char)x)+Character.toString((char)y));

    }
}
