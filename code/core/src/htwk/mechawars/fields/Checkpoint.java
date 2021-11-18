package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

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

    @Override
    public String toString() {
        return "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", number: " + this.number;
    }

    /**
     * Take the Number of the Checkpoint.
     * @return the Number
     */
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
    public Robot action(Robot robot) {
        robot.setbackupCopyX(robot.getXcoor());
        robot.setbackupCopyY(robot.getYcoor());         
        checkPointChoice(robot);
        return robot;       
    }
    
    /**
     * function to check if the order in which the Checkpoints are reached is right.
     */
    
    public void checkPointChoice(Robot robot) {
        if (robot.getCheckPointNumber() == this.number) {
            if (this.number == 8) {
                GameScreen.setWinCondition(true);
            }
            robot.incCheckPointNumber();
        }
    }
}
