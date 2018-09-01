package Rating;

import Board.*;
import MoveGenerator.DangerChecker;
import Parameters.Parameters;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by FKPro on 07.07.2018.
 */
public class BoardRater {
    private static final int KINGINDANGER_VALUE = -500;

    private static final int PAWN_VALUE = 1000;
    private static final int KNIGHT_VALUE = 3250;
    private static final int BISHOP_VALUE = 3250;
    private static final int ROOK_VALUE = 5000;
    private static final int QUEEN_VALUE = 9750;
    private static final int KING_VALUE = 999999;
    private static final int BOTH_BISHOPS_VALUE = 500;

    private static final int POSITION_DANGERED_VALUE = 2;

    public static int getBoardRating(ChessBoard chessboard){
        int rating = 0;

        rating = rating + getMaterialRating(chessboard);
        rating = rating + getKingDangerRating(chessboard);
        rating = rating + getDangerPositionsRating(chessboard);

        if(Parameters.randomizerValue > 0) {
            Random rd = new Random();
            rating += rd.nextInt(Parameters.randomizerValue);
        }

        return rating;
    }

    public static int getDangerPositionsRating(ChessBoard chessboard){
        int rating = 0;
        ArrayList<Position> whiteDangerPositions = DangerChecker.getDangerPositionsWithoutDuplicates(chessboard,false);
        ArrayList<Position> blackDangerPositions = DangerChecker.getDangerPositionsWithoutDuplicates(chessboard, true);

        rating = (whiteDangerPositions.size() * POSITION_DANGERED_VALUE ) - (blackDangerPositions.size() * POSITION_DANGERED_VALUE );
        return rating;
    }

    public static int getKingDangerRating(ChessBoard chessboard){
        int rating = 0;

        if(chessboard.isKinginDanger(true)){
            rating = rating + KINGINDANGER_VALUE;
        }
        if(chessboard.isKinginDanger(false)){
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
}
