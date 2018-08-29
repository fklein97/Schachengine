package MoveEvaluation;

import Board.*;
import MoveGenerator.MoveGenerator;
import Rating.BoardRater;

import java.util.ArrayList;

public class Min_Max_Tree {

    private Node root;

    public Min_Max_Tree(ChessBoard chessboard, boolean maximize){
        this.root = new Node(chessboard, maximize, -1000000, 1000000);
    }

    public Min_Max_Tree(Node root){
        this.root = root;
    }

    public void generate_Tree(int depth){
        if (root.getMaximize()){
            maximize(depth,root,root.getMinRating(), root.getMaxRating());
        } else {
            minimize(depth,root,root.getMinRating(),root.getMaxRating());
        }
    }



    private int maximize(int depth, Node current, int min, int max){
        int value = 0;
        if (depth >= 1){
            ArrayList<Position> position = new ArrayList();
            position.addAll(current.getChessBoard().getPositionsCopy());
            for(Position p : position){
                if(p.getPiece().isWhite()) {
                    ArrayList<Position> moveset = MoveGenerator.getMoveSet(p, current.getChessBoard());
                    ChessBoard currentBoard = new ChessBoard(current.getChessBoard().getPositionsCopy());
                    for (Position k : moveset) {
                        currentBoard.move(p, k);
                        Node next = new Node(currentBoard, false, current, p, k, max, min);
                        current.addChild(next);
                        value = minimize(depth - 1, next, min, max);
                        if (value >= current.getRating()) {
                            current.setRating(value);
                        }
                        if (value >= current.getMaxRating()) {
                            current.setMaxRating(value);
                        }
                        if (value >= current.getMinRating()){
                            return value;
                        }

                    }
                }

            }

        } else {
          return BoardRater.getBoardRating(current.getChessBoard());
        }
        return current.getRating();
    }

    private int minimize(int depth, Node current,int min, int max){
        int value = 0;
        if (depth >= 1){
            ArrayList<Position> posiiton = new ArrayList();
            posiiton.addAll(current.getChessBoard().getPositionsCopy());
            for(Position p : posiiton){
                if(p.getPiece().isWhite() == false) {
                    ArrayList<Position> moveset = MoveGenerator.getMoveSet(p, current.getChessBoard());
                    ChessBoard currentBoard = new ChessBoard(current.getChessBoard().getPositionsCopy());
                    for (Position k : moveset) {

                        currentBoard.move(p, k);
                        Node next = new Node(currentBoard, true, current, p, k, max, min);
                        current.addChild(next);
                        value = maximize(depth - 1, next, min, max);
                        if (value <= current.getRating()) {
                            current.setRating(value);
                        }
                        if (value <= current.getMinRating()) {
                            current.setMinRating(value);
                        }
                        if(value <= current.getMaxRating()){
                            return value;
                        }

                    }
                }

            }

        } else {
            return BoardRater.getBoardRating(current.getChessBoard());
        }
        return current.getRating();
    };

    public Move getBestMove(){
        int rating = 0;
        Move bestMove = null;
        ArrayList<Node> children = root.getChildren();
        if(root.getMaximize() == true){
            rating = -1000000;
            for(Node n : children){
                if(n.getRating() >= rating){
                    rating = n.getRating();
                    bestMove = n.getMove();
                }
            }
        }
        else{
            rating = 1000000;
            for(Node n : children){
                if(n.getRating() <= rating){
                    rating = n.getRating();
                    bestMove = n.getMove();
                }
            }

        }
        return bestMove;

    }
}
