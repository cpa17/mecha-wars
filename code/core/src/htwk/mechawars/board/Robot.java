package htwk.mechawars.board;

/**
 * Class that presents the robot.
 */

public class Robot {
    private Dir dir;
    private int x;
    private int y;

    public void robot() {
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Robot moveInDirection(byte mov) {
        switch (getDir()) {
            case NORTH:
                setY(getY() - mov);
                return this;
            case SOUTH:
                setY(getY() + mov);
                return this;
            case EAST:
                setX(getX() + mov);
                return this;
            case WEST:
                setX(getX() - mov);
                return this;
            default:
                return this;
        }
    }

    public Robot turn(byte rightTurnCount) {
        int t = (getDir().getValue() + rightTurnCount);
        if (t < 5){
            setDir(getDir().intToDirection(t % 5));
        } else {
            setDir(getDir().intToDirection((t % 5) + 1));
        }

        return this;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
