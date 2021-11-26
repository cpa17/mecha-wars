package htwk.mechawars.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private int backupCopyX;
    private int backupCopyY;
    private int checkPointNumber = 1;
    private boolean shutDownMark;
    private boolean backupDraw;
    private boolean lastRound;
    private boolean nextRound;
    private boolean destroyed;
    private Texture life;
    private Texture damage;
    private Texture shutDown;
    private Texture hud;

    /**
     * Constructor of the robot class.
     */
    public Robot() {
        backupCopyX = 0;
        backupCopyY = 0;
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

    public int getbackupCopyX() {
        return backupCopyX;
    }

    public int getbackupCopyY() {
        return backupCopyY;
    }
    
    public int getCheckPointNumber() {
        return checkPointNumber;
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

    public void setbackupCopyX(int position) {
        backupCopyX = position;
    }

    public void setbackupCopyY(int position) {
        backupCopyY = position;
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

    public void lifeDown() {
        lifePoints -= 1;
    }
    
    public void incCheckPointNumber() {
        checkPointNumber++;
    }

    /**
     * Updates the life texture depening on the current lifePoints of the robot.
     */
    private void updateLife() {
        switch (lifePoints) {
            case 0 :    life = new Texture(Gdx.files.internal("parameters/hp0.png"));
                        break;
                        
            case 1 :    life = new Texture(Gdx.files.internal("parameters/hp1.png"));
                        break;
                        
            case 2 :    life = new Texture(Gdx.files.internal("parameters/hp2.png"));
                        break;
                        
            case 3 :    life = new Texture(Gdx.files.internal("parameters/hp3.png"));
                        break;
                        
            default:    break;
        }
    }
    
    /**
     * Updates the damage texture depening on the current damagePoints of the robot.
     */
    private void updateDamage() {
        switch (damagePoints) {
            case 0 :    damage = new Texture(Gdx.files.internal("parameters/damage0.png"));
                        break;
                        
            case 1 :    damage = new Texture(Gdx.files.internal("parameters/damage1.png"));
                        break;
                        
            case 2 :    damage = new Texture(Gdx.files.internal("parameters/damage2.png"));
                        break;
                        
            case 3 :    damage = new Texture(Gdx.files.internal("parameters/damage3.png"));
                        break;
                        
            case 4 :    damage = new Texture(Gdx.files.internal("parameters/damage4.png"));
                        break;
                        
            case 5 :    damage = new Texture(Gdx.files.internal("parameters/damage5.png"));
                        break;
                        
            case 6 :    damage = new Texture(Gdx.files.internal("parameters/damage6.png"));
                        break;
                        
            case 7 :    damage = new Texture(Gdx.files.internal("parameters/damage7.png"));
                        break;
                        
            case 8 :    damage = new Texture(Gdx.files.internal("parameters/damage8.png"));
                        break;
                        
            case 9 :    damage = new Texture(Gdx.files.internal("parameters/damage9.png"));
                        break;

            case 10:    damage = new Texture(Gdx.files.internal("parameters/damage10.png"));
                        backupDraw = true;
                        break;

            default:    break;
        }
    }
    
    /**
     * Updates the shutDown texture depending on the state of the shutDownMark.
     */
    private void updateShutDown() {
        if (shutDownMark) {
            shutDown = new Texture(Gdx.files.internal("parameters/sleep.png"));
        } else {
            shutDown = new Texture(Gdx.files.internal("parameters/wakeup.png"));
        }
    }

    /**
     * Updates the shutDown texture depending on the amout of players.
     */
    private void createHud() {
        hud = new Texture(Gdx.files.internal("parameters/hud.png"));
    }

    /**
     * Draws the parameter textures. 
     */
    public void drawParameters(SpriteBatch batch) {
        createHud();
        updateLife();
        updateDamage();
        updateShutDown();
        updateShutDown();
        if (backupDraw) {
            backupDraw = false;
            batch.draw(new Texture(Gdx.files.internal("robot.png")), backupCopyX, backupCopyY);
        }
        batch.draw(hud, 754, 15);
        batch.draw(life, 763, 23);
        batch.draw(damage, 838, 23);
        batch.draw(shutDown, 914, 23);
    }

    /**
     * Function that draws the robot on the playing field.
     */
    public void drawRobot(Sprite sprite, Board board) {
        int tileSize = (Gdx.graphics.getHeight() / board.matrix.length);
        int x = xcoor;
        int y = Math.abs(ycoor - (board.matrix.length - 1));

        if (dir == Dir.NORTH) {
            sprite.setPosition(tileSize * x, tileSize * y);
            sprite.setRotation(0);
        } else if (dir == Dir.EAST) {
            sprite.setPosition(tileSize * x, tileSize * y);
            sprite.setRotation(270);
        } else if (dir == Dir.SOUTH) {
            sprite.setPosition(tileSize * x, tileSize * y);
            sprite.setRotation(180);
        } else if (dir == Dir.WEST) {
            sprite.setPosition(tileSize * x, tileSize * y);
            sprite.setRotation(90);
        }
    }
}
