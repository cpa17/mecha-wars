package htwk.mechawars.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class that presents the robot and the player.
 */

public class Robot {
    private Dir dir;
    private int xcoor;
    private int ycoor;
    private int startX;
    private int startY;
    private int lifePoints;
    private int damagePoints;
    private boolean shutDownMark;
    private Texture life;
    private Texture damage;
    private Texture shutDown;
    
    /**
     * Constructor of the robot class.
     */
    public Robot() {
        lifePoints = 1;
        damagePoints = 1;
        shutDownMark = true;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    /**
     * Method that lets the robot run forward.
     * @param mov byte of move
     * @return new position
     */
    public Robot moveInDirection(byte mov) {
        switch (getDir()) {
            case NORTH:
                setYcoor(getYcoor() - mov);
                return this;
            case SOUTH:
                setYcoor(getYcoor() + mov);
                return this;
            case EAST:
                setXcoor(getXcoor() + mov);
                return this;
            case WEST:
                setXcoor(getXcoor() - mov);
                return this;
            default:
                return this;
        }
    }

    /**
     * Method that makes robots turn.
     * @param rightTurnCount byte of turn moves
     * @return new direction
     */
    public Robot turn(byte rightTurnCount) {
        int t = (getDir().getValue() + rightTurnCount);
        if (t < 5) {
            setDir(getDir().intToDirection(t % 5));
        } else {
            setDir(getDir().intToDirection((t % 5) + 1));
        }

        return this;
    }

    public void setYcoor(int ycoor) {
        this.ycoor = ycoor;
    }

    public void setXcoor(int xcoor) {
        this.xcoor = xcoor;
    }

    public int getYcoor() {
        return ycoor;
    }

    public int getXcoor() {
        return xcoor;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
    
    /**
     * Getters.
     */
    public int getDp() {
        return damagePoints;
    }

    public int getLp() {
        return lifePoints;
    }

    public boolean getSd() {
        return shutDownMark;
    }
    
    /**
     * Setters.
     */
    public void damageUp() {
        damagePoints += 1;
    }
    
    public void damageReset() {
        damagePoints = 0;
    }

    public void lifeUp() {
        lifePoints += 1;
    }
    
    public void lifeDown() {
        lifePoints -= 1;
    }

    public void setShutDown(boolean on) {
        shutDownMark = on;
    }

    /**
     * Updates the life texture depening on the current lifePoints of the robot.
     */
    private void updateLife() {
        switch (lifePoints) {
            case 0 :    life = new Texture(Gdx.files.internal("life0.png"));
                        break;
                        
            case 1 :    life = new Texture(Gdx.files.internal("life1.png"));
                        break;
                        
            case 2 :    life = new Texture(Gdx.files.internal("life2.png"));
                        break;
                        
            case 3 :    life = new Texture(Gdx.files.internal("life3.png"));
                        break;
                        
            default:    break;
        }
    }
    
    /**
     * Updates the damage texture depening on the current damagePoints of the robot.
     */
    private void updateDamage() {
        switch (damagePoints) {
            case 0 :    damage = new Texture(Gdx.files.internal("damage0.png"));
                        break;
                        
            case 1 :    damage = new Texture(Gdx.files.internal("damage1.png"));
                        break;
                        
            case 2 :    damage = new Texture(Gdx.files.internal("damage2.png"));
                        break;
                        
            case 3 :    damage = new Texture(Gdx.files.internal("damage3.png"));
                        break;
                        
            case 4 :    damage = new Texture(Gdx.files.internal("damage4.png"));
                        break;
                        
            case 5 :    damage = new Texture(Gdx.files.internal("damage5.png"));
                        break;
                        
            case 6 :    damage = new Texture(Gdx.files.internal("damage6.png"));
                        break;
                        
            case 7 :    damage = new Texture(Gdx.files.internal("damage7.png"));
                        break;
                        
            case 8 :    damage = new Texture(Gdx.files.internal("damage8.png"));
                        break;
                        
            case 9 :    damage = new Texture(Gdx.files.internal("damage9.png"));
                        break;
                        
            default:    break;
        }
    }
    
    /**
     * Updates the shutDown texture depending on the state of the shutDownMark.
     */
    private void updateShutDown() {
        if (shutDownMark) {
            shutDown = new Texture(Gdx.files.internal("shutdownOn.png"));
        } else {
            shutDown = new Texture(Gdx.files.internal("shutdownOff.png"));
        }
    }
    
    /**
     * Draws the parameter textures. 
     */
    public void drawParameters(SpriteBatch batch) {
        updateLife();
        updateDamage();
        updateShutDown();
        batch.draw(life, 730, 0, 50, 50);
        batch.draw(damage, 730, 80, 50, 50);
        batch.draw(shutDown, 730, 160, 50, 50);
    }
}
