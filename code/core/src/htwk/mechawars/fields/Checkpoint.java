package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Board;
import htwk.mechawars.board.Robot;
import htwk.mechawars.game.GameScreen;

/**
 * Class of a Checkpoint.
 */
public class Checkpoint extends Field {

    // attribute which indicates the number of a certain checkpoint
    private int number;
    private Texture tile;

    /**
     * Constructor of a Checkpoint.
     */
    public Checkpoint(int xcoor, int ycoor, int number) {
        super(xcoor, ycoor);
        this.number = number;
        setCheckpoint();
    }

    /**
     * Constructor of a Checkpoint which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public Checkpoint(int xcoor, int ycoor, int number, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.number = number;

        if (!isTest) {
            setCheckpoint();
        }
    }

    @Override
    public String toString() {
        return "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", number: " + this.number;
    }

    public int getNumber() {
        return this.number;
    }

    public Texture getTile() {
        return this.tile;
    }
    
    /**
     * changeScreen function to switch to GameScreen.
     */
    public void setCheckpoint() {
        switch (this.number) {
            case 1 :    this.tile = new Texture("mapAssets/checkpoints/Check1.png");
                        break;
    
            case 2 :    this.tile = new Texture("mapAssets/checkpoints/Check2.png");
                        break;
    
            case 3 :    this.tile = new Texture("mapAssets/checkpoints/Check3.png");
                        break;
    
            case 4 :    this.tile = new Texture("mapAssets/checkpoints/Check4.png");
                        break;
                    
            case 5 :    this.tile = new Texture("mapAssets/checkpoints/Check5.png");
                        break;
                    
            case 6 :    this.tile = new Texture("mapAssets/checkpoints/Check6.png");
                        break;
                    
            case 7 :    this.tile = new Texture("mapAssets/checkpoints/Check7.png");
                        break;
                    
            case 8 :    this.tile = new Texture("mapAssets/checkpoints/Check8.png");
                        break;
                    
            default:    break;
        }
    }
    
    @Override
    public Robot cardAction(Robot robot) {
        checkPointChoice(robot);
        return robot;       
    }
    
    @Override
    public Robot turnAction(Robot robot) {
        return robot;       
    }
    
    /**
     * function to check if the order in which the Checkpoints are reached is right.
     */
    public void checkPointChoice(Robot robot) {
        if (robot.getCheckPointNumber() == this.number) {
            if (this.number == Board.checkpoint) {
                GameScreen.setWinCondition(true);
            }
            robot.setbackupCopyX(robot.getXcoor());
            robot.setbackupCopyY(robot.getYcoor());
            robot.incCheckPointNumber();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Checkpoint other = (Checkpoint) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
            return false;
        }
        if (number != other.number) {
            return false;
        }

        return true;
    }

    // Function that overwrites the hash code but has no further meaning or functionality
    // It only has to exist for the pipeline to work with the overridden equals function
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42;
    }
}
