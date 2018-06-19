/**
 * Created by FKPro on 18.06.2018.
 */
public class MoveGenerator {

    /**
     *
     * @param currentPos
     * @return
     */
    public Position[] getMoveSet(Position currentPos){
        Position[] moveset = null;
        if(currentPos.getPiece() instanceof Pawn){
            moveset = getPawnMoves(currentPos);
        }

        return moveset;
    }

    /**
     *
     * @param currentPos
     * @return
     */
    private Position[] getPawnMoves(Position currentPos){
        Position[] moveset = null;
        Pawn p = (Pawn)currentPos.getPiece();

        if(p.isWhite() == true){
            if(p.moved() == false){
                moveset[0] = new Position(currentPos.getX(),currentPos.getY() + 1, currentPos.getPiece());
                moveset[1] = new Position(currentPos.getX(), currentPos.getY() + 2, currentPos.getPiece());
                return moveset;
            }
            else{
                if(currentPos.getY() + 1 <= 8){
                    moveset[0] = new Position(currentPos.getX(),currentPos.getY() + 1, currentPos.getPiece());
                }
                return moveset;
            }
        }
        else{
            if(p.moved() == false){
                moveset[0] = new Position(currentPos.getX(),currentPos.getY() - 1, currentPos.getPiece());
                moveset[1] = new Position(currentPos.getX(), currentPos.getY() - 2, currentPos.getPiece());
                return moveset;
            }
            else{
                if(currentPos.getY() - 1 >= 1){
                    moveset[0] = new Position(currentPos.getX(),currentPos.getY() - 1, currentPos.getPiece());
                }
                return moveset;
            }
        }
    }
}