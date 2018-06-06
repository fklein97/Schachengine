
public class Position {

    private int x;
    private int y;

    public Position(int x, int y){
        if(x < 1 || x > 8){
            throw new RuntimeException(Constant.WRONG_POSITION);
        }
        if(y < 1 || y > 8){
            throw new RuntimeException(Constant.WRONG_POSITION);
        }
        this.x = x;
        this.y = y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }


}
