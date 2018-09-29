package UCI;

import Parameters.Parameters;
/**
 * Class to manage the remaining time and set the max time for finding a best move
 */
public class TimeManager {

    private double engineTime;
    private double playerTime;
    //Avg number of moves per gamemode
    private int moveNumber = 20;

    /**
     * Returns time left for player
     * @return time left for player
     */
    public double getPlayerTime() {
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
    public double getEngineTime() {
        return engineTime;
    }

    /**
     * Sets time left for engine
     * @param engineTime time left engine
     */
    public void setEngineTime(int engineTime) {
        this.engineTime = engineTime;
        if (moveNumber == 0){
            moveNumber = engineTime/20000;
        }
        setTurnTime();
    }

    /**
     * Sets the time the engine has for the next turn
     */
    public void setTurnTime() {
        Parameters.turnTime = (this.engineTime / moveNumber);
        System.out.println(engineTime + " " + Parameters.turnTime);
        if (moveNumber > 1) {
            moveNumber--;
        }
    }
}
