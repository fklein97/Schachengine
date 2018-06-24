import Board.*;
import MoveGenerator.*;

import java.util.ArrayList;
import java.util.Scanner;

import static MoveGenerator.MoveGenerator.getMoveSet;

/**
 * Dialog class to test the MoveGenerator
 * @version 1.1
 * @author florian
 */
public class MoveGeneratorDialog {

    public static ChessBoard chessboard;
    public static Scanner input;

    /**
     * starts the dialog
     * @param args commandline parameters
     */
    public static void main(String[] args){
        dialog();
    }

    /**
     * dialog method
     */
    private static void dialog(){
        try {
            input = new Scanner(System.in);
            chessboard = new ChessBoard();
            chessboard.setPositions(new ArrayList<Position>());
            int i = 999;
            while(i != 9){
                System.out.print("What do u want to do? \n");
                System.out.print("1: Add a Chesspiece to the Board\n");
                System.out.print("2: Get Moveset of a Chesspiece\n");
                System.out.print("9: Close this program\n");
                System.out.print("\n");
                printBoard();

                i = input.nextInt();

                if(i == 1){
                    addChessPiece();
                }
                else if(i == 2){
                    getMoveset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * adds a chesspiece to the testboard
     */
    private static void addChessPiece(){
        int x;
        int y;
        boolean isWhite;
        String pieceinput;
        ArrayList<Position> board = chessboard.getPositions();

        System.out.print("x?\n");
        x = input.nextInt();
        System.out.print("y?\n");
        y = input.nextInt();
        System.out.print("What Piece? (P,p,R,r,N,n,B,b...)");
        pieceinput = input.next();
        if(pieceinput.equals("P")){
            board.add(new Position(x,y,new Pawn(true)));
        }
        else if(pieceinput.equals("p")){
            board.add(new Position(x,y,new Pawn(false)));
        }
        else if(pieceinput.equals("R")){
            board.add(new Position(x,y,new Rook(true)));
        }
        else if(pieceinput.equals("r")){
            board.add(new Position(x,y,new Rook(false)));
        }
        else if(pieceinput.equals("N")){
            board.add(new Position(x,y,new Knight(true)));
        }
        else if(pieceinput.equals("n")){
            board.add(new Position(x,y,new Knight(false)));
        }
        else if(pieceinput.equals("B")){
            board.add(new Position(x,y,new Bishop(true)));
        }
        else if(pieceinput.equals("b")){
            board.add(new Position(x,y,new Bishop(false)));
        }
        else if(pieceinput.equals("Q")){
            board.add(new Position(x,y,new Queen(true)));
        }
        else if(pieceinput.equals("q")){
            board.add(new Position(x,y,new Queen(false)));
        }
        else if(pieceinput.equals("K")){
            board.add(new Position(x,y,new King(true)));
        }
        else if(pieceinput.equals("k")){
            board.add(new Position(x,y,new King(false)));
        }

        chessboard.setPositions(board);
    }

    /**
     * prints the moveset of a chesspiece on the testboard
     */
    private static void getMoveset(){
        System.out.print("Moveset of which chesspiece?\n\n");
        int pieces_length = chessboard.getPositions().toArray().length;
        Position[] pieces = chessboard.getPositions().toArray(new Position[chessboard.getPositions().size()]);
        for(int i = 0; i < pieces_length; i++){
            System.out.print(i+ " - "+ pieces[i].getPiece().toString() +" x:" + pieces[i].getX() + " y:" + pieces[i].getY());
            System.out.print("\n");
        }
        int choice = input.nextInt();

        ArrayList<Position> moveset = new ArrayList<Position>();
        moveset.addAll(getMoveSet(pieces[choice],chessboard));
        Position[] moveset_array = moveset.toArray(new Position[moveset.size()]);
        System.out.print("the selected chesspiece can go to:\n");
        for(int i = 0; i < moveset_array.length; i++){
            System.out.print("x:" + moveset_array[i].getX() + " y:" + moveset_array[i].getY() + "\n");
        }
    }

    /**
     * prints the testboard
     */
    private static void printBoard(){
        System.out.print("0 1 2 3 4 5 6 7 8\n");
        printBoardRow(8);
        printBoardRow(7);
        printBoardRow(6);
        printBoardRow(5);
        printBoardRow(4);
        printBoardRow(3);
        printBoardRow(2);
        printBoardRow(1);
        System.out.print("\n");
    }

    /**
     * prints a row of the testboard
     * @param row row that should be printed
     */
    private static void printBoardRow(int row){
        System.out.print(row + " ");
        for(int i = 1; i <= 8; i++){
            ChessPiece nextPiece = chessboard.chessPieceAt(i,row);
            if(nextPiece != null) {
                if (nextPiece instanceof Pawn) {
                    if (nextPiece.isWhite()) {
                        System.out.print("P ");
                    } else {
                        System.out.print("p ");
                    }
                }
                else if(nextPiece instanceof Rook){
                    if(nextPiece.isWhite()){
                        System.out.print("R ");
                    }
                    else{
                        System.out.print("r ");
                    }
                }
                else if(nextPiece instanceof Bishop){
                    if(nextPiece.isWhite()){
                        System.out.print("B ");
                    }
                    else{
                        System.out.print("b ");
                    }
                }
                else if(nextPiece instanceof Knight){
                    if(nextPiece.isWhite()){
                        System.out.print("N ");
                    }
                    else{
                        System.out.print("n ");
                    }
                }
                else if(nextPiece instanceof Queen){
                    if(nextPiece.isWhite()){
                        System.out.print("Q ");
                    }
                    else{
                        System.out.print("q ");
                    }
                }
                else if(nextPiece instanceof King){
                    if(nextPiece.isWhite()){
                        System.out.print("K ");
                    }
                    else{
                        System.out.print("k ");
                    }
                }
            }
            else{
                System.out.print("O ");
            }
        }
        System.out.print("\n");
    }

}
