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
    private boolean lastRound;
    private boolean nextRound;
    private boolean destroyed;
    private Texture life;
    private Texture damage;
    private Texture shutDown;
    
    /**
     * Constructor of the robot class.
     */
    public Robot() {
        lifePoints = 3;
        damagePoints = 0;
        shutDownMark = false;
        lastRound = false;
        nextRound = false;
        destroyed = false;
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

    /**
     * Getters.
     */
    public int getDamagePoints() {
        return damagePoints;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public boolean getShutDown() {
        return shutDownMark;
    }

    public Dir getDir() {
        return dir;
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

    public boolean getLastRound() {
        return lastRound;
    }

    public boolean getNextRound() {
        return nextRound;
    }

    public boolean getDestroyed() {
        return destroyed;
    }

    /**
     * Setters.
     */
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setShutDown(boolean on) {
        shutDownMark = on;
    }

    public void setYcoor(int ycoor) {
        this.ycoor = ycoor;
    }

    public void setXcoor(int xcoor) {
        this.xcoor = xcoor;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setLastRound(boolean lastRound) {
        this.lastRound = lastRound;
    }

    public void setNextRound(boolean nextRound) {
        this.nextRound = nextRound;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }



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

    /**
     * Updates the life texture depening on the current lifePoints of the robot.
     */
    private void updateLife() {
        switch (lifePoints) {
            case 0 :    life = new Texture(Gdx.files.internal("platzhalter.png"));
                        break;
                        
            case 1 :    life = new Texture(Gdx.files.internal("background2.png"));
                        break;
                        
            case 2 :    life = new Texture(Gdx.files.internal("background3.png"));
                        break;
                        
            case 3 :    life = new Texture(Gdx.files.internal("background4.png"));
                        break;
                        
            default:    break;
        }
    }
    
    /**
     * Updates the damage texture depening on the current damagePoints of the robot.
     */
    private void updateDamage() {
        switch (damagePoints) {
            case 0 :    damage = new Texture(Gdx.files.internal("platzhalter.png"));
                        break;
                        
            case 1 :    damage = new Texture(Gdx.files.internal("background6.png"));
                        break;
                        
            case 2 :    damage = new Texture(Gdx.files.internal("background7.png"));
                        break;
                        
            case 3 :    damage = new Texture(Gdx.files.internal("background8.png"));
                        break;
                        
            case 4 :    damage = new Texture(Gdx.files.internal("background9.png"));
                        break;
                        
            case 5 :    damage = new Texture(Gdx.files.internal("background10.png"));
                        break;
                        
            case 6 :    damage = new Texture(Gdx.files.internal("background11.png"));
                        break;
                        
            case 7 :    damage = new Texture(Gdx.files.internal("background12.png"));
                        break;
                        
            case 8 :    damage = new Texture(Gdx.files.internal("background13.png"));
                        break;
                        
            case 9 :    damage = new Texture(Gdx.files.internal("background14.png"));
                        break;
                        
            default:    break;
        }
    }
    
    /**
     * Updates the shutDown texture depending on the state of the shutDownMark.
     */
    private void updateShutDown() {
        if (shutDownMark) {
            shutDown = new Texture(Gdx.files.internal("12.jpg"));
        } else {
            shutDown = new Texture(Gdx.files.internal("platzhalter.png"));
        }
    }
    
    /**
     * Draws the parameter textures. 
     */
    public void drawParameters(SpriteBatch batch) {
        updateLife();
        updateDamage();
        updateShutDown();
        batch.draw(life, 0, 0, 200, 200);
        batch.draw(damage, 400, 0, 200, 200);
        batch.draw(shutDown, 600, 0, 200, 200);
    }
}
