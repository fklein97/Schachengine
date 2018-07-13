package MoveEvaluation;

import Board.*;
import MoveGenerator.MoveGenerator;
import Rating.BoardRater;


import java.util.ArrayList;

public class MinMaxTree {

    private Node root;
    private int maxRating;
    private int minRating;

    public MinMaxTree (ChessBoard chessBoard, boolean maximize){
        root        = new Node(chessBoard, maximize);
        maxRating   = -1000000;
        minRating   = 1000000;
        //root.setParent(root);
    }

    /**
     *
     *
     * @param maxDepth maximal depth of the tree
     * @param currentNode   starting node for the tree
     */
    public int  generateTree(int maxDepth, Node currentNode){
        ChessBoard chessBoard               = currentNode.getChessBoard();
        ArrayList<Position> positions       =  chessBoard.getPositions();
        ArrayList<Position> moveset;
        int rating = 0;
        if(maxDepth > 0){
            for(int j = 0; j < positions.size(); j++){                                                                                                                                         //test each position
                //System.out.println("J-Count: " + j +"Position Sizie: " + positions.size() + " Depth: " + maxDepth +" Rating: " + BoardRater.getMaterialRating(currentNode.getChessBoard()));
                //currentNode.getChessBoard().print();
                if( positions.get(j).getPiece().isWhite() && currentNode.getMaximize()){                                                                                            // white oder maximieren
                    moveset = MoveGenerator.getMoveSet(positions.get(j), chessBoard);
                    for(int i = 0; i < moveset.size(); i++){                                                                                                                            //create node for each move
                        ChessBoard board = new ChessBoard(chessBoard.getPositions());
                        board.move(positions.get(j), moveset.get(i));
                        Node childNode = new Node(board, false,currentNode, positions.get(j), moveset.get(i), currentNode.getMaxRating(), currentNode.getMinRating());
                        //board.print();

                        currentNode.addChild(childNode);
                        rating = generateTree((maxDepth-1), childNode);
                        update(currentNode, rating);
                        if(currentNode.getRating() >= currentNode.getMinRating()){
                            //System.out.println("Pruning in depth: " + maxDepth + " on a MaxNote, Nr: ");
                            return currentNode.getMinRating();
                        }

                    }
                }

                if( !positions.get(j).getPiece().isWhite() && !currentNode.getMaximize()){                                                                                            // black oder nicht maximieren
                    moveset = MoveGenerator.getMoveSet(positions.get(j), chessBoard);
                    for(int i = 0; i < moveset.size(); i++){                                                                                                                            //create node for each move
                        ChessBoard board = new ChessBoard(chessBoard.getPositions());
                        board.move(positions.get(j), moveset.get(i));
                        Node childNode = new Node(board, true,currentNode, positions.get(j), moveset.get(i), currentNode.getMaxRating(), currentNode.getMinRating());
                        //board.print();
                        currentNode.addChild(childNode);

                        rating = generateTree((maxDepth-1), childNode);
                        update(currentNode, rating);
                        if(currentNode.getRating() <= currentNode.getMaxRating()){
                            //System.out.println("Pruning in depth: " + maxDepth + " on a MinNote, Nr: ");
                            return currentNode.getMaxRating();
                        }

                    }
                }
            }

        }
        else{
            //System.out.println("LeafRating" + BoardRater.getMaterialRating(currentNode.getChessBoard()));
            return BoardRater.getMaterialRating(currentNode.getChessBoard());

        }

        return BoardRater.getMaterialRating(currentNode.getChessBoard());

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
        boolean max = root.getMaximize();
        if(max){
            rating = -1000000;
        }
        else{
            rating = 1000000;
        }
        ArrayList<Node> children = root.getChildren();
        Move bestMove = new Move(null, null);//children.get(2).getMove();                                                                     //!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        for(Node child : children){
            if(max){
                if(child.getRating() >= rating){
                    rating = child.getRating();
                    bestMove = child.getMove();
                }
            }
            else{
                if(child.getRating() <= rating){
                    rating = child.getRating();
                    bestMove = child.getMove();
                }
            }
        }
        return bestMove;
    }


    public Node getRoot(){
        return root;
    }

    public void setRoot(Node root){
        this.root = root;
    }                                                                                   //(!position.getPiece().isWhite() && ((currentNode.getDepth() % 2 ) != 0) )


    private void update(Node node, int rating){
        if(node.getMaximize()){
            if(rating >= node.getRating()){
                node.setRating(rating);
            }
            if(rating >= node.getMaxRating()){
                node.setMaxRating(rating);
            }
        }else{
            if(rating <= node.getRating()){
                node.setRating(rating);
            }
            if(rating <= node.getMinRating()){
                node.setMinRating(rating);
            }
        }


    }

}
