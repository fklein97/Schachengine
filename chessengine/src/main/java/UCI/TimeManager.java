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

    public int getPlayerTime() {
        return playerTime;
    }

    public void setPlayerTime(int playerTime) {
        this.playerTime = playerTime;
    }

    public int getEngineTime() {
        return engineTime;
    }

    public void setEngineTime(int engineTime) {
        this.engineTime = engineTime;
    }

    public void setTurnTime(){
        if (moveNumber > 1)
        moveNumber--;
        Parameters.turnTime = engineTime/moveNumber;
    }
}
