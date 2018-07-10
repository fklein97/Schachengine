package Board;


import java.util.ArrayList;

/**
 *  Structure to manage the history of boards
 */
public class DataManager {

    public DataManager(){
        this.history = new ArrayList<ChessBoard>();
    }

    private ArrayList<ChessBoard> history;

    public ArrayList<ChessBoard> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<ChessBoard> history) {
        this.history = history;
    }

    /**
     * @return the last element in the list
     */
    public ChessBoard getLast() {
        return history.get(history.size()-1);
    }

    /**
     * Adds the..
     * @param newBoard
     * .. to the history
     */
    public void addBoard(ChessBoard newBoard){
        history.add(newBoard);
    }

    /**
     * Disbands the history to a certain index
     */
    public void disbandHistory(){
        //Remisbedingung nicht erfüllt -> Löschen der history
    }
}
