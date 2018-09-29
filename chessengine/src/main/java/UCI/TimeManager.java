package UCI;

import Parameters.Parameters;
/**
 * Class to manage the remaining time and set the max time for finding a best move
 */
public class TimeManager {

    private double engineTime;
    private double playerTime;
    //Avg number of moves per gamemode
    private int moveNumber = 0;

    public double getPlayerTime() {
        return playerTime;
    }

    public void setPlayerTime(int playerTime) {
        this.playerTime = playerTime;
    }

    public double getEngineTime() {
        return engineTime;
    }

    public void setEngineTime(int engineTime) {
        this.engineTime = engineTime;
        if (moveNumber == 0){
            moveNumber = engineTime/20000;
        }
        setTurnTime();
    }

    public void setTurnTime() {
        Parameters.turnTime = (this.engineTime / moveNumber);
        System.out.println(engineTime + " " + Parameters.turnTime);
        if (moveNumber > 1) {
            moveNumber--;
        }
    }
}
