package UCI;

import Parameters.Parameters;
/**
 * Class to manage the remaining time and set the max time for finding a best move
 */
public class TimeManager {

    private int engineTime;
    private int playerTime;
    //Avg number of moves per gamemode
    private int moveNumber = 20;

    /**
     * Returns time left for player
     * @return time left for player
     */
    public int getPlayerTime() {
        return playerTime;
    }

    /**
     * Sets time left for player
     * @param playerTime time left for player
     */
    public void setPlayerTime(int playerTime) {
        this.playerTime = playerTime;
    }

    /**
     * Returns time left for engine
     * @return time left for engine
     */
    public int getEngineTime() {
        return engineTime;
    }

    /**
     * Sets time left for engine
     * @param engineTime time left engine
     */
    public void setEngineTime(int engineTime) {
        this.engineTime = engineTime;
    }

    /**
     * Sets the time the engine has for the next turn
     */
    public void setTurnTime(){
        if (moveNumber > 1)
        moveNumber--;
        Parameters.turnTime = engineTime/moveNumber;
    }
}
