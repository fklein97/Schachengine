package MoveEvaluation;

import Board.*;
import MoveGenerator.MoveGenerator;

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
        ChessBoard chessBoard       = currentNode.getChessBoard();
        Position[] positions        = (Position[]) chessBoard.getPositions().toArray();
        ArrayList<Position> moveset;
        if(maxDepth > 0){
            for(Position position : positions){                                                                                                                                         //test each position
                if( position.getPiece().isWhite() && currentNode.getMaximize()){                                                                                            // white oder maximieren
                    moveset = MoveGenerator.getMoveSet(position, chessBoard);
                    for(int i = 0; 0 < moveset.size(); i++){                                                                                                                            //create node for each move
                        ChessBoard board = new ChessBoard(chessBoard.getPositions());
                        board.move(position, moveset.get(i));
                        Node childNode = new Node(board, false,currentNode, position, moveset.get(i));
                        currentNode.addChild(childNode);
                        generateTree((maxDepth-1), childNode);
                    }
                }

                if( !position.getPiece().isWhite() && !currentNode.getMaximize()){                                                                                            // black oder nicht maximieren
                    moveset = MoveGenerator.getMoveSet(position, chessBoard);
                    for(int i = 0; 0 < moveset.size(); i++){                                                                                                                            //create node for each move
                        ChessBoard board = new ChessBoard(chessBoard.getPositions());
                        board.move(position, moveset.get(i));
                        Node childNode = new Node(board, true,currentNode, position, moveset.get(i));
                        currentNode.addChild(childNode);
                        generateTree((maxDepth-1), childNode);
                    }
                }

            }
        }
        else{
            currentNode.rate();
        }

    }

    /**
     *
     * @return best known move for current tree
     */
    public Move getBestMove(){
        int rating = 100000000;
        ArrayList<Node> children = root.getChildren();
        Move bestMove = children.get(0).getMove();

        for(Node child : children){
            if(child.getRating() <= rating){
                bestMove = child.getMove();
            }
        }
        return bestMove;
    }


                                                                                                                                    //(!position.getPiece().isWhite() && ((currentNode.getDepth() % 2 ) != 0) )

}
