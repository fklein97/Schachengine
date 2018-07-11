package MoveEvaluation;

import Board.*;
import MoveGenerator.MoveGenerator;
import Rating.BoardRater;


import java.util.ArrayList;

public class MinMaxTree {

    private Node root;

    public MinMaxTree (ChessBoard chessBoard){
        root = new Node(chessBoard);
    }

    /**
     *
     *
     * @param maxDepth maximal depth of the tree
     * @param currentNode   starting node for the tree
     */
    public void generateTree(int maxDepth, Node currentNode){
        ChessBoard chessBoard               = currentNode.getChessBoard();
        ArrayList<Position> positions       =  chessBoard.getPositions();
        ArrayList<Position> moveset;
        if(maxDepth > 0){
            for(int j = 0; j < positions.size(); j++){                                                                                                                                         //test each position
                //System.out.println("J-Count: " + j +"Position Sizie: " + positions.size() + " Depth: " + maxDepth);
                //currentNode.getChessBoard().print();
                if( positions.get(j).getPiece().isWhite() && currentNode.getMaximize()){                                                                                            // white oder maximieren
                    moveset = MoveGenerator.getMoveSet(positions.get(j), chessBoard);
                    for(int i = 0; i < moveset.size(); i++){                                                                                                                            //create node for each move
                        ChessBoard board = new ChessBoard(chessBoard.getPositions());
                        board.move(positions.get(j), moveset.get(i));
                        Node childNode = new Node(board, false,currentNode, positions.get(j), moveset.get(i));
                        //board.print();

                        currentNode.addChild(childNode);
                        if(BoardRater.getMaterialRating(childNode.getChessBoard()) > currentNode.getRating()){
                            generateTree((maxDepth-1), childNode);

                        }
                        else{
                            childNode.rate();
                        }
                    }
                }

                if( !positions.get(j).getPiece().isWhite() && !currentNode.getMaximize()){                                                                                            // black oder nicht maximieren
                    moveset = MoveGenerator.getMoveSet(positions.get(j), chessBoard);
                    for(int i = 0; i < moveset.size(); i++){                                                                                                                            //create node for each move
                        ChessBoard board = new ChessBoard(chessBoard.getPositions());
                        board.move(positions.get(j), moveset.get(i));
                        Node childNode = new Node(board, true,currentNode, positions.get(j), moveset.get(i));
                        //board.print();
                        currentNode.addChild(childNode);

                        if(BoardRater.getMaterialRating(childNode.getChessBoard()) < currentNode.getRating()){
                            generateTree((maxDepth-1), childNode);

                        }
                        else{
                            childNode.rate();
                        }

                    }
                }
            }

        }
        else{
            currentNode.rate();
        }

    }

    public void generateTree(int maxDepth){
        generateTree(maxDepth, root);
    }

    /**
     *
     * @return best known move for current tree
     */
    public Move getBestMove(){
        int rating = 0;
        if(root.getMaximize()){
            rating = -1000000;
        }
        else{
            rating = 1000000;
        }
        ArrayList<Node> children = root.getChildren();
        Move bestMove = children.get(0).getMove();                                                                     //!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        for(Node child : children){
            if(child.getRating() > rating){
                rating = child.getRating();
                bestMove = child.getMove();
            }
        }
        return bestMove;
    }


    public Node getRoot(){
        return root;
    }

    public void setRoot(Node root){
        this.root = root;
    }
                                                                                                                                    //(!position.getPiece().isWhite() && ((currentNode.getDepth() % 2 ) != 0) )

}
