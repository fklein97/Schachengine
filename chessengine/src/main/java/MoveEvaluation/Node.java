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

    public Node (ChessBoard chessBoard, boolean maximize, Node parent, Position posFrom, Position posTo, int maxRating, int minRating){
        this.chessBoard     = chessBoard;
        this.parent         = parent;
        this.posFrom        = posFrom;
        this.posTo          = posTo;
        this.maximize       = maximize;
        this.children       = new ArrayList<Node>();
        initRating();
        this.maxRating      = maxRating;
        this.minRating      = minRating;
    }

    public Node (ChessBoard chessBoard, boolean maximize, Node parent, Position posFrom, Position posTo){
        this.chessBoard     = chessBoard;
        this.parent         = parent;
        this.posFrom        = posFrom;
        this.posTo          = posTo;
        this.maximize       = maximize;
        this.children       = new ArrayList<Node>();
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

    public Node(ChessBoard chessBoard, boolean maximize, int max, int min ){
        this(chessBoard, maximize, null, null, null);
    }

    /**
     * Initialize the worst possible rating.
     * If Node tries to maximize inizialisze with negative
     * If Node tries to minimize inizialisze with positive
     */
    private void initRating(){
        if(maximize){
            this.rating = -1000000;
        }
        else{
            this.rating = 1000000;
        }
        maxRating = -1000000;
        minRating =  1000000;
    }

    /**
     * gets boardrating from BoardRater und updates it to its parent
     */
    public void rate(){
        int rating  = getBoardRating(chessBoard);
        this.rating = rating;
        if(parent != null){
            parent.updateRating(rating, this.maxRating, this.minRating);
        }

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
    private void updateRating(int rating, int maxRating, int minRating){
        if(maximize){
            if(rating >= this.rating){
                this.rating = rating;
                this.maxRating = maxRating;
                if(this.parent != null){
                    parent.updateRating(rating, maxRating, minRating);
                }
            }
        }
        if(!maximize){
            if(rating <= this.rating){
                this.rating = rating;
                this.minRating = minRating;
                if(this.parent != null){
                    parent.updateRating(rating, maxRating, minRating);
                }
            }
        }

    }

    public void setRating(int rating){
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

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void setChessBoard(ChessBoard chessboard) {this.chessBoard = chessboard; }

    public int getMinRating(){
        return minRating;
    }
    public int getMaxRating(){
        return maxRating;
    }


}
