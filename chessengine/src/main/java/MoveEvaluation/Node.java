package MoveEvaluation;

import Board.*;
import Rating.BoardRater;

import java.util.ArrayList;

import static Rating.BoardRater.*;


public class Node {
    private int minRating;
    private int maxRating;
    private int rating;
    private int depth;
    private Position posFrom;
    private Position posTo;
    private ChessBoard chessBoard;
    private Node parent;
    private ArrayList<Node> children;
    private boolean maximize;


    public Node (ChessBoard chessBoard, boolean maximize, Node parent, Position posFrom, Position posTo){
        this.chessBoard     = chessBoard;
        this.parent         = parent;
        this.posFrom        = posFrom;
        this.posTo          = posTo;
        this.maximize       = maximize;
        initRating();
    }

    public Node(ChessBoard chessBoard,  boolean max, Position posFrom, Position posTo){
        this(chessBoard, max, null, posFrom, posTo);
    }
    public Node(ChessBoard chessBoard, Node parent, Position posFrom, Position posTo){
        this(chessBoard, true, parent, posFrom, posTo);
    }
    public Node(ChessBoard chessBoard){
        this(chessBoard, true, null, null, null);
    }

    /**
     * Initialize the worst possible rating.
     * If depth is even inizialisze with negative
     * If depth is odd inizialisze with positive
     */
    private void initRating(){
        if((depth % 2) == 0){
            this.rating = -1000000;
        }
        else{
            this.rating = 1000000;
        }
    }

    /**
     * gets boardrating from BoardRater und updates it to its parent
     */
    public void rate(){
        int rating  = getMaterialRating(chessBoard);
        this.rating = rating;
        parent.updateRating(rating);
    }


    public void setMinRating(int minRating){
        this.minRating = minRating;
    }
    public void setMaxRating(int maxRating){
        this.maxRating = maxRating;
    }

    public Node getParent(){
        return  this.parent;
    }

    public int getRating(){
        return this.rating;
    }

    /**
     * Gets new Rating from children and Updates ist Rating. Gives new Rating to Parents, but only if current rating was chanced
     * @param rating
     */
    private void updateRating(int rating){
        if((depth % 2) == 0){
            if(rating > this.rating){
                setRating(rating);
                if(this.parent != null){
                    parent.updateRating(rating);
                }
            }
        }
        if((depth % 2) != 0){
            if(rating < this.rating){
                setRating(rating);
                if(this.parent != null){
                    parent.updateRating(rating);
                }
            }
        }

    }

    private void setRating(int rating){
        this.rating = rating;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void addChild(Node node){
        this.children.add(node);
    }

    public int getDepth(){
        return depth;
    }

    public boolean getMaximize(){
        return maximize;
    }

    public ArrayList<Node> getChildren(){
        return children;
    }

    public Move getMove(){
        return (new Move(posFrom, posTo));
    }
}
