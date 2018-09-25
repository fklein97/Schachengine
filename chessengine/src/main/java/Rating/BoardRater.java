package Rating;

import Board.*;
import MoveGenerator.DangerChecker;
import Parameters.*;
import org.omg.CORBA.FREE_MEM;

import java.util.ArrayList;
import java.util.Random;

/**
 * Rates Chessboards
 * @author Florian Klein, Moritz Grill
 */
public class BoardRater {
    private static final int KINGINDANGER_VALUE     = -500;

    private static final int PAWN_VALUE             = 1000;
    private static final int KNIGHT_VALUE           = 3250;
    private static final int BISHOP_VALUE           = 3250;
    private static final int ROOK_VALUE             = 5000;
    private static final int QUEEN_VALUE            = 9750;
    private static final int KING_VALUE             = 999999;
    private static final int BOTH_BISHOPS_VALUE     = 500;
    private static final int DOUBLE_PAWN_PENALTY    = 250;
    private static final int FREE_PAWN_VALUE        = 500;

    private static final int POSITION_DANGERED_VALUE = 3;

    private final static int[][][] PAWN_POSITION_VALUE = {
            {
                    {  0,   0,   0,    0,   0,   0,   0,   0 },
                    { 50, 100,  100,-200,-200, 100, 100,  50},
                    { 50, -50, -100,   0,   0,-100, -50,  50},
                    { 0 ,  0 ,    0, 200, 200,   0,   0,   0},
                    { 50,  50,  100, 250, 250, 100,  50,  50},
                    { 100, 100, 200, 300, 300, 200, 100, 100},
                    { 500, 500, 500, 500, 500, 500, 500, 500},
                    { 0,  0   ,  0 ,   0,   0,   0,   0,   0}
            },{
                    {   0,   0,   0,   0,   0,   0,   0,   0},
                    { 500, 500, 500, 500, 500, 500, 500, 500},
                    { 100, 100, 200, 300, 300, 200, 100, 100},
                    {  50,  50, 100, 250, 250, 100,  50,  50},
                    {   0,   0,   0, 200, 200,   0,   0,   0},
                    {  50, -50,-100,   0,   0,-100, -50,  50},
                    {  50, 100, 100,-200,-200, 100, 100,  50},
                    {   0,   0,   0,   0,   0,   0,   0,   0}
    }
    };

    private static final int[][][] KNIGHT_POSITION_VALUE = {
            {
                    { -500,-400,-300,-300,-300,-300,-400,-500 },
                    { -400,-200,   0,  50,  50,   0,-200,-400 },
                    { -300,  50, 100, 150, 150, 100,  50,-300 },
                    { -300,   0, 150, 200, 200, 150,   0,-300 },
                    { -300,  50, 150, 200, 200, 150,  50,-300 },
                    { -300,   0, 100, 150, 150, 100,   0,-300 },
                    { -400,-200,   0,   0,   0,   0,-200,-400 },
                    { -500,-400,-300,-300,-300,-300,-400,-500 }
            },{
                    { -500,-400,-300,-300,-300,-300,-400,-500 },
                    { -400,-200,   0,   0,   0,   0,-200,-400 },
                    { -300,   0, 100, 150, 150, 100,   0,-300 },
                    { -300,  50, 150, 200, 200, 150,  50,-300 },
                    { -300,   0, 150, 200, 200, 150,   0,-300},
                    { -300,  50, 100, 150, 150, 100,  50,-300 },
                    { -400,-200,   0,  50,  50,   0,-200,-400 },
                    { -500,-400,-300,-300,-300,-300,-400,-500 }
    }
    };


    private static final int[][][] BISHOP_POSITION_VALUE = {
            {
                    { -200,-100,-100,-100,-100,-100,-100,-200 },
                    { -100,  50,   0,   0,   0,   0,  50,-100 },
                    { -100, 100, 100, 100, 100, 100, 100,-100 },
                    { -100,   0, 100, 100, 100, 100,   0,-100 },
                    { -100,  50,  50, 100, 100,  50,  50,-100 },
                    { -100,   0,  50, 100, 100,  50,   0,-100 },
                    { -100,   0,   0,   0,   0,   0,   0,-100 },
                    { -200,-100,-100,-100,-100,-100,-100,-200 }
            },{
                    { -200,-100,-100,-100,-100,-100,-100,-200 },
                    { -100,   0,   0,   0,   0,   0,   0,-100 },
                    { -100,   0,  50, 100, 100,  50,   0,-100 },
                    { -100,  50,  50, 100, 100,  50,  50,-100},
                    { -100,   0, 100, 100, 100, 100,   0,-100 },
                    { -100, 100, 100, 100, 100, 100, 100,-100 },
                    { -100,  50,   0,   0,   0,   0,  50,-100 },
                    { -200,-100,-100,-100,-100,-100,-100,-200 }
    }
    };

    private static final int[][][] ROOK_POSITION_VALUE = {
            {
                    {   0,   0,   0,  50,  50,   0,   0,   0 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    {  50, 100, 100, 100, 100, 100, 100,  50 },
                    {   0,   0,   0,  50,  50,   0,   0,   0 }
            },{
                    {   0,   0,   0,   0,   0,   0,   0,   0 },
                    {  50, 100, 100, 100, 100, 100, 100,  50 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    { -50,   0,   0,   0,   0,   0,   0, -50 },
                    {   0,   0,   0,  50,  50,   0,   0,   0 }
    }
    };

    private static final int[][][] QUEEN_POSITION_VALUE = {
            {
                    { -200,-100,-100, -50, -50,-100,-100,-200 },
                    { -100,   0,  50,   0,   0,   0,   0,-100 },
                    { -100,  50,  50,  50,  50,  50,   0,-100 },
                    {    0,   0,  50,  50,  50,  50,   0, -50 },
                    {  -50,   0,  50,  50,  50,  50,   0, -50 },
                    { -100,   0,  50,  50,  50,  50,   0,-100 },
                    { -100,   0,   0,   0,   0,   0,   0,-100 },
                    { -200,-100,-100, -50, -50,-100,-100,-200 }
            },{
                    { -200,-100,-100, -50, -50,-100,-100,-200 },
                    { -100,   0,   0,   0,   0,  0,    0,-100 },
                    { -100,   0,  50,  50,  50,  50,   0,-100 },
                    {  -50,   0,  50,  50,  50,  50,   0, -50 },
                    {    0,   0,  50,  50,  50,  50,   0, -50 },
                    { -100,  50,  50,  50,  50,  50,   0,-100 },
                    { -100,   0,  50,   0,   0,   0,   0,-100 },
                    { -200,-100,-100, -50, -50,-100,-100,-200 }
    }
    };

    private static final int[][][] KING_POSITION_VALUE = {
            {
                    {  200, 300,  -50,   0,   0,  -50, 300, 200 },
                    {  200, 200,    0,   0,   0,    0, 200, 200 },
                    { -100,-200, -200,-200,-200, -200,-200,-100 },
                    { -200,-300, -300,-400,-400, -300,-300,-200 },
                    { -300,-400, -400,-500,-500, -400,-400,-300 },
                    { -300,-400, -400,-500,-500, -400,-400,-300 },
                    { -300,-400, -400,-500,-500, -400,-400,-300 },
                    { -300,-400, -400,-500,-500, -400,-400,-300 }
            },{
                    { -300,-400, -400,-500,-500, -400,-400,-300 },
                    { -300,-400, -400,-500,-500, -400,-400,-300 },
                    { -300,-400, -400,-500,-500, -400,-400,-300 },
                    { -300,-400, -400,-500,-500, -400,-400,-300 },
                    { -200,-300, -300,-400,-400, -300,-300,-200 },
                    { -100,-200, -200,-200,-200, -200,-200,-100 },
                    {  200, 200,    0,   0,   0,    0, 200, 200 },
                    {  200, 300,  -50,   0,   0,  -50, 300, 200 }
    }
    };

    /**
     * Generates the combined Boardrating for a Chessboard
     * @param chessboard Chessboard that should be rated
     * @return rating of the board
     */
    public static int getBoardRating(ChessBoard chessboard){
        int rating = 0;
        int white_bishops = 0;
        int black_bishops = 0;
        Boolean free = true;
        ArrayList<Position> whitePawnList = new ArrayList<Position>();
        ArrayList<Position> blackPawnList = new ArrayList<Position>();
        ArrayList<Position> positions = chessboard.getPositions();

        for (Position p: positions) {
            int multiplier;
            if(p.getPiece().isWhite()){
                multiplier = 1;
                if(p.getPiece() instanceof  Bishop && Parameters.useMaterialRating){
                    white_bishops++;
                }
                else if(p.getPiece() instanceof Pawn && Parameters.usePawnStructureRating){
                    whitePawnList.add(p);
                }
            }
            else{
                multiplier = -1;
                if(p.getPiece() instanceof Bishop && Parameters.useMaterialRating){
                    black_bishops++;
                }
                else if(p.getPiece() instanceof Pawn && Parameters.usePawnStructureRating){
                    blackPawnList.add(p);
                }
            }

            // Position Rating
            if(Parameters.usePositionRating) {
                rating = rating + getPiecePositionRating(p);
            }

            // Free Pawn + Double Pawn
            if(Parameters.usePawnStructureRating) {
                if (p.getPiece().isWhite() && p.getPiece() instanceof Pawn) {
                    for (Position po : positions) {
                        if(po.getPiece() instanceof  Pawn) {
                            if ((p.getX() == po.getX() || p.getX() == po.getX() + 1 || p.getX() == po.getX() - 1) && !po.getPiece().isWhite()) {
                                free = false;
                            }
                            if (p.getX() == po.getX() && p.getY() != po.getY() && po.getPiece().isWhite()) {
                                rating = rating - DOUBLE_PAWN_PENALTY;

                            }
                        }
                    }
                    if (free == true) {
                        rating = rating + FREE_PAWN_VALUE;
                    }
                    free = true;
                }
                if (!p.getPiece().isWhite() && p.getPiece() instanceof Pawn) {
                    for (Position po : positions) {
                        if(po.getPiece() instanceof  Pawn) {
                            if ((p.getX() == po.getX() || p.getX() == po.getX() + 1 || p.getX() == po.getX() - 1) && po.getPiece().isWhite()) {
                                free = false;
                            }
                            if (p.getX() == po.getX() && p.getY() != po.getY() && !po.getPiece().isWhite()) {
                                rating = rating + DOUBLE_PAWN_PENALTY;

                            }
                        }
                    }
                    if (free == true) {
                        rating = rating - FREE_PAWN_VALUE;
                    }
                    free = true;
                }
            }



            // Material Rating
            if(Parameters.useMaterialRating) {
                if (p.getPiece() instanceof Pawn) {
                    rating = rating + (multiplier * PAWN_VALUE);
                } else if (p.getPiece() instanceof Knight) {
                    rating = rating + (multiplier * KNIGHT_VALUE);
                } else if (p.getPiece() instanceof Bishop) {
                    rating = rating + (multiplier * BISHOP_VALUE);
                } else if (p.getPiece() instanceof Rook) {
                    rating = rating + (multiplier * ROOK_VALUE);
                } else if (p.getPiece() instanceof Queen) {
                    rating = rating + (multiplier * QUEEN_VALUE);
                } else if (p.getPiece() instanceof King) {
                    rating = rating + (multiplier * KING_VALUE);
                }
            }

        }

        if(Parameters.useMaterialRating) {
            if (white_bishops >= 2) {
                rating = rating + BOTH_BISHOPS_VALUE;
            }
            if (black_bishops >= 2) {
                rating = rating - BOTH_BISHOPS_VALUE;
            }
        }

        //KINGINDANGER RATING
        if(Parameters.useKinginDangerRating) {
            if (positions.size() <= Parameters.KinginDangerPieceCount) {
                rating = rating + getKingDangerRating(chessboard);
            }
        }

        //DANGERPOSITIONS RATING
        if(Parameters.useDangerPositionsRating) {
            rating = rating + getDangerPositionsRating(chessboard);
        }

        //RANDOMIZER
        if(Parameters.randomizerValue > 0) {
            Random rd = new Random();
            rating += rd.nextInt(Parameters.randomizerValue);
        }

        return rating;
    }

    /**
     * Generates the boardrating for chessboard using all rating-methods seperated
     * @param chessboard Chessboard that should be rated
     * @return boardrating
     */
    public static int getSeperateBoardRating(ChessBoard chessboard){
        int rating = 0;

        if(Parameters.useMaterialRating) {
            rating = rating + getMaterialRating(chessboard);
        }

        if(Parameters.useKinginDangerRating) {
            if (chessboard.getPositions().size() <= Parameters.KinginDangerPieceCount) {
                rating = rating + getKingDangerRating(chessboard);
            }
        }

        if(Parameters.useDangerPositionsRating) {
            rating = rating + getDangerPositionsRating(chessboard);
        }

        if(Parameters.usePositionRating) {
            rating = rating + getPositionRating(chessboard);
        }

        if(Parameters.usePawnStructureRating) {
            rating = rating + getPawnStructureValue(chessboard);
        }

        if(Parameters.randomizerValue > 0) {
            Random rd = new Random();
            rating += rd.nextInt(Parameters.randomizerValue);
        }

        return rating;
    }

    /**
     * Rates a chessboard based on positions that are endangered
     * @param chessboard Chessboard that should be rated
     * @return Boardrating
     */
    public static int getDangerPositionsRating(ChessBoard chessboard){
        int rating = 0;
        ArrayList<Position> whiteDangerPositions = DangerChecker.getDangerPositionsWithoutDuplicates(chessboard,false);
        ArrayList<Position> blackDangerPositions = DangerChecker.getDangerPositionsWithoutDuplicates(chessboard, true);

        rating = (whiteDangerPositions.size() * POSITION_DANGERED_VALUE ) - (blackDangerPositions.size() * POSITION_DANGERED_VALUE );
        return rating;
    }

    /**
     * Rates a chessboard based on kings in danger
     * @param chessboard Chessboard that should be rated
     * @return Boardrating
     */
    public static int getKingDangerRating(ChessBoard chessboard){
        int rating = 0;

        if(chessboard.isKinginDanger(true)){
            rating = rating + KINGINDANGER_VALUE;
        }
        else if(chessboard.isKinginDanger(false)){
            rating = rating - KINGINDANGER_VALUE;
        }

        return rating;
    }

    /**
     * Rates a chessboard based on piece values.
     * Uses int so 1 Pawn = 100
     * Values based on "The Kaufman Piece Values"
     * @param chessboard Chessboard that should be rated
     * @return Rating of the chessboard (positiv = white leads; negativ = black leads)
     */
    public static int getMaterialRating(ChessBoard chessboard){
        int rating = 0;
        int white_bishops = 0;
        int black_bishops = 0;

        ArrayList<Position> positions = chessboard.getPositions();

        for (Position p: positions) {
            int multiplier;
            if(p.getPiece().isWhite()){
                multiplier = 1;
                if(p.getPiece() instanceof  Bishop){
                    white_bishops++;
                }
            }
            else{
                multiplier = -1;
                if(p.getPiece() instanceof Bishop){
                    black_bishops++;
                }
            }

            if(p.getPiece() instanceof Pawn){
                rating = rating + (multiplier * PAWN_VALUE);
            }
            else if(p.getPiece() instanceof Knight){
                rating = rating + (multiplier * KNIGHT_VALUE);
            }
            else if(p.getPiece() instanceof Bishop){
                rating = rating + (multiplier * BISHOP_VALUE);
            }
            else if(p.getPiece() instanceof Rook){
                rating = rating + (multiplier * ROOK_VALUE);
            }
            else if(p.getPiece() instanceof Queen){
                rating = rating + (multiplier * QUEEN_VALUE);
            }
            else if(p.getPiece() instanceof King){
                rating = rating + (multiplier * KING_VALUE);
            }
        }

        if(white_bishops >= 2){
            rating = rating + BOTH_BISHOPS_VALUE;
        }
        if(black_bishops >= 2){
            rating = rating - BOTH_BISHOPS_VALUE;
        }

        return rating;
    }

    /**
     * Rates a chessboard based on pawnstructure
     * @param chessBoard Chessboard that should be rated
     * @return boardrating
     */
    public static int getPawnStructureValue(ChessBoard chessBoard){
        int rating = 0;
        ArrayList<Position> positions = chessBoard.getPositions();
        ArrayList<Position> blackPawnList = new ArrayList<Position>();
        ArrayList<Position> whitePawnList = new ArrayList<Position>();

        for(Position p : positions){
            if(p.getPiece().toString().equals("white pawn")){
                whitePawnList.add(p);
            }
            else if(p.getPiece().toString().equals("black pawn")){
                blackPawnList.add(p);
            }
        }


        rating = rating + doublePawn(whitePawnList, blackPawnList);
        rating = rating + freePawn(whitePawnList, blackPawnList);
        return rating;
    }

    private static int freePawn(ArrayList<Position> whitePawnList, ArrayList<Position> blackPawnList){
        int rating = 0;
        boolean free = true;
        if(whitePawnList.size() < 6 && blackPawnList.size() < 6){
                for(int i = 0; i < whitePawnList.size(); i++){
                    for(int j = 0; j < blackPawnList.size()  ;j++){
                        if(whitePawnList.get(i).getX() == blackPawnList.get(j).getX() || whitePawnList.get(i).getX() == blackPawnList.get(j).getX()+1 || whitePawnList.get(i).getX() == blackPawnList.get(j).getX()-1){
                            free = false;
                        }
                    }
                    if(free == true){
                        rating = rating + FREE_PAWN_VALUE;
                    }
                    free = true;
                }
                for(int i = 0; i < blackPawnList.size(); i++){
                    for(int j = 0; j < whitePawnList.size()  ;j++) {
                        if (blackPawnList.get(i).getX() == whitePawnList.get(j).getX() || blackPawnList.get(i).getX() == whitePawnList.get(j).getX() + 1 || blackPawnList.get(i).getX() == whitePawnList.get(j).getX() - 1) {
                            free = false;
                        }
                    }
                        if(free == true){
                            rating = rating - FREE_PAWN_VALUE;
                        }
                        free = true;
                    }

        }

        return rating;
    }

    private static int doublePawn(ArrayList<Position> whitePawnList, ArrayList<Position> blackPawnList){
        int rating = 0;
        int black = 0;
        int white = 0;

        for(int i = 0; i < whitePawnList.size(); i++){
            for(int j = i+1; j < whitePawnList.size()  ;j++){
                if(whitePawnList.get(i).getX() == whitePawnList.get(j).getX()){
                    white++;
                }
            }
        }
        for(int i = 0; i < blackPawnList.size(); i++){
            for(int j = i+1; j < blackPawnList.size()  ;j++){
                if(blackPawnList.get(i).getX() == blackPawnList.get(j).getX()){
                    black++;
                }
            }
        }
        rating = (-1 * DOUBLE_PAWN_PENALTY * white) + (DOUBLE_PAWN_PENALTY * black);
        return rating;
    }

    /**
     * Rates a chessboard based on position-maps
     * @param chessBoard chessboard that should be rated
     * @return Boardrating
     */
    public static int getPositionRating(ChessBoard chessBoard){
        int rating = 0;
        ArrayList<Position> positions =  chessBoard.getPositions();
        for(Position p : positions){
            rating = rating + getPiecePositionRating(p);
        }

        return rating;
    }

    private static int getPiecePositionRating(Position position){
        ChessPiece  chessPiece   = position.getPiece();
        int         rating       = 0;

        if(chessPiece.isWhite()){
            switch(chessPiece.toString()){
                case Strings.WHITE_PAWN:
                    rating = PAWN_POSITION_VALUE[0][position.getY()-1][position.getX()-1];
                    break;
                case Strings.WHITE_ROOK:
                    rating = ROOK_POSITION_VALUE[0][position.getY()-1][position.getX()-1];
                    break;
                case Strings.WHITE_BISHOP:
                    rating = BISHOP_POSITION_VALUE[0][position.getY()-1][position.getX()-1];
                    break;
                case Strings.WHITE_KNIGHT:
                    rating = KNIGHT_POSITION_VALUE[0][position.getY()-1][position.getX()-1];
                    break;
                case Strings.WHITE_QUEEN:
                    rating = QUEEN_POSITION_VALUE[0][position.getY()-1][position.getX()-1];
                    break;
                case Strings.WHITE_KING:
                    rating = KING_POSITION_VALUE[0][position.getY()-1][position.getX()-1];
                    break;
            }
        }else {
            switch (chessPiece.toString()) {
                case Strings.BLACK_PAWN:
                    rating = PAWN_POSITION_VALUE[1][position.getY()-1][position.getX()-1];
                    break;
                case Strings.BLACK_ROOK:
                    rating = ROOK_POSITION_VALUE[1][position.getY()-1][position.getX()-1];
                    break;
                case Strings.BLACK_BISHOP:
                    rating = BISHOP_POSITION_VALUE[1][position.getY()-1][position.getX()-1];
                    break;
                case Strings.BLACK_KNIGHT:
                    rating = KNIGHT_POSITION_VALUE[1][position.getY()-1][position.getX()-1];
                    break;
                case Strings.BLACK_QUEEN:
                    rating = QUEEN_POSITION_VALUE[1][position.getY()-1][position.getX()-1];
                    break;
                case Strings.BLACK_KING:
                    rating = KING_POSITION_VALUE[1][position.getY()-1][position.getX()-1];
                    break;
            }
            rating = rating * -1;

        }
        rating = rating / 2;
        return rating;
    }



}

