package Board;

public class Constant {

    public final static int     SIDE_LENGTH     = 8;
    public final static int     CHESS_PIECES    = 32;
    public final static String  WRONG_POSITION  = "Wrong position for chess piece!";

    public final static int     PAWN_VALUE      = 100;
    public final static int     ROOK_VALUE      = 500;
    public final static int     KNIGHT_VALUE    = 300;
    public final static int     BISHOP_VALUE    = 300;
    public final static int     QUEEN_VALUE     = 900;
    public final static int     KING_VALUE      = 10000;

    // Set Moves for castlings

    public final static Position WHITE_CASTLING_LONG_FROM = new Position(5,1, new King(true, false));
    public final static Position WHITE_CASTLING_LONG_TO = new Position(3,1, new King(true, true));
    public final static Position WHITE_CASTLING_LONG_ROOK_FROM = new Position(5,1, new Rook(true, false));
    public final static Position WHITE_CASTLING_LONG_ROOK_TO = new Position(5,1, new Rook(true, true));

    public final static Position WHITE_CASTLING_SHORT_FROM = new Position(5,1, new King(true, false));
    public final static Position WHITE_CASTLING_SHORT_TO = new Position(6,1, new King(true, true));
    public final static Position WHITE_CASTLING_SHORT_ROOK_FROM = new Position(5,1, new Rook(true, false));
    public final static Position WHITE_CASTLING_SHORT_ROOK_TO = new Position(5,1, new Rook(true, true));

    public final static Position BLACK_CASTLING_LONG_FROM = new Position(5,1, new King(false, false));
    public final static Position BLACK_CASTLING_LONG_TO = new Position(5,1, new King(false, true));
    public final static Position BLACK_CASTLING_LONG_ROOK_FROM = new Position(5,1, new Rook(false, false));
    public final static Position BLACK_CASTLING_LONG_ROOK_TO = new Position(5,1, new Rook(false, true));

    public final static Position BLACK_CASTLING_SHORT_FROM = new Position(5,1, new King(false, false));
    public final static Position BLACK_CASTLING_SHORT_TO = new Position(6,1, new King(false, true));
    public final static Position BLACK_CASTLING_SHORT_ROOK_FROM = new Position(5,1, new Rook(false, false));
    public final static Position BLACK_CASTLING_SHORT_ROOK_TO = new Position(5,1, new Rook(false, true));
}
