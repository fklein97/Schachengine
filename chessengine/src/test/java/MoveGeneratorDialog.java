import Board.*;
import MoveGenerator.*;

import java.util.ArrayList;
import java.util.Random;
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
                System.out.print("1: Add a chesspiece to the board\n");
                System.out.print("2: Get moveset of a chesspiece\n");
                System.out.print("3: Randomize the chessboard\n");
                System.out.print("4: Clear the chessboard\n");
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
                else if(i == 3){
                    randomizeBoard();
                }
                else if(i == 4){
                    clearBoard();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void clearBoard() {
        chessboard.setPositions(new ArrayList<Position>());
    }

    private static void randomizeBoard() {
        ArrayList<Position> randomPositions = new ArrayList<>();

        for(int i = 1; i <= 20; i++){
            int random_x = new Random().nextInt(7) + 1;
            int random_y = new Random().nextInt(7) + 1;
            int random_piece_number = new Random().nextInt(11);
            ChessPiece random_piece;
            switch (random_piece_number){
                case 0: random_piece = new Pawn(true);
                        break;
                case 1: random_piece = new Pawn(false);
                        break;
                case 2: random_piece = new Rook(true);
                        break;
                case 3: random_piece = new Rook(false);
                        break;
                case 4: random_piece = new Bishop(true);
                        break;
                case 5: random_piece = new Bishop(false);
                        break;
                case 6: random_piece = new King(true);
                        break;
                case 7: random_piece = new King(false);
                        break;
                case 8: random_piece = new Knight(true);
                        break;
                case 9: random_piece = new Knight(false);
                        break;
                case 10:random_piece = new Queen(true);
                        break;
                case 11:random_piece = new Queen(false);
                        break;
                default:random_piece = new Pawn(true);
                        break;
            }
            chessboard.setPositions(randomPositions);
            if(chessboard.chessPieceAt(random_x,random_y) == null){
                randomPositions.add(new Position(random_x,random_y,random_piece));
            }
        }

        chessboard.setPositions(randomPositions);
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
        chessboard.print();
    }

    /**
     * prints a row of the testboard
     * @param row row that should be printed
     */


}
