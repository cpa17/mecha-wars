package htwk.mechawars.board;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import htwk.mechawars.ConfigReader;
import htwk.mechawars.cards.Card;

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
    private static Robot[] players = createRobots(ConfigReader.getPlayerNumber());
    private LinkedList<Card> selectedCards = new LinkedList<Card>();
    
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

    // Getters. ------------------------------------------------------------------------------

    /**
     * Getter-Function to get the current amount of damagePoints.
     *
     * @return the current damagePoints as an Integer (int)
     */
    public int getDamagePoints() {
        return damagePoints;
    }

    /**
     * Getter-Function to get the current amount of lifePoints.
     *
     * @return the current lifePoints as an Integer (int)
     */
    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * Getter-Function to get the current status of the robot.
     *
     * @return the status of the robot (active/shutdown or not)
     */
    public boolean getShutDown() {
        return shutDownMark;
    }

    /**
     * Getter-Function to get the current direction of the robot.
     *
     * @return the direction of the robot (as an Enum Dir)
     */
    public Dir getDir() {
        return dir;
    }

    /**
     * Getter-Function to get the current y-coordinate of the robot.
     *
     * @return the y-coordinate as an int
     */
    public int getYcoor() {
        return ycoor;
    }

    /**
     * Getter-Function to get the current x-coordinate of the robot.
     *
     * @return the x-coordinate as an int
     */
    public int getXcoor() {
        return xcoor;
    }

    /**
     * Getter-Function to get the startposition (x-coordinate) of the robot.
     *
     * @return the x-coordinate of the startposition as an int
     */
    public int getStartX() {
        return startX;
    }

    /**
     * Getter-Function to get the startposition (y-coordinate) of the robot.
     *
     * @return the y-coordinate of the startposition as an int
     */
    public int getStartY() {
        return startY;
    }

    /**
     * Getter-Function to get ... .
     *
     * @return the ?
     */
    public boolean getLastRound() {
        return lastRound;
    }

    /**
     * Getter-Function to get ... .
     *
     * @return the ?
     */
    public boolean getNextRound() {
        return nextRound;
    }

    /**
     * Getter-Function to get the status of destruction of the robot (true or not).
     *
     * @return the status of the robot (destroyed or not) as a boolean
     */
    public boolean getDestroyed() {
        return destroyed;
    }

    /**
     * Getter-Function to get the x-coordinate of BackupCopy from the robot.
     *
     * @return the x-coordinate of the BackupCopy (int)
     */
    public int getbackupCopyX() {
        return backupCopyX;
    }

    /**
     * Getter-Function to get the y-coordinate of BackupCopy from the robot.
     *
     * @return the y-coordinate of the BackupCopy (int)
     */
    public int getbackupCopyY() {
        return backupCopyY;
    }

    public int getCheckPointNumber() {
        return checkPointNumber;
    }

    // Setters. ------------------------------------------------------------------------------

    /**
     * Setter-Function for the direction of the robot.
     *
     * @param dir -> direction of the robot
     */

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    /**
     * Setter-Function for the active-status of the robot.
     *
     * @param on -> boolean that encodes the (active-)status
     */
    public void setShutDown(boolean on) {
        shutDownMark = on;
    }

    /**
     * Setter-Function for the y-coordinate of the robot.
     *
     * @param ycoor -> Integer of the y-coordinate
     */
    public void setYcoor(int ycoor) {
        this.ycoor = ycoor;
    }

    /**
     * Setter-Function for the x-coordinate of the robot.
     *
     * @param xcoor -> Integer of the x-coordinate
     */
    public void setXcoor(int xcoor) {
        this.xcoor = xcoor;
    }

    /**
     * Setter-Function to set the startposition (x-coordinate) of the robot.
     *
     * @param startX -> Integer of the x-coordinate
     */
    public void setStartX(int startX) {
        this.startX = startX;
    }

    /**
     * Setter-Function to set the startposition (y-coordinate) of the robot.
     *
     * @param startY -> Integer of the y-coordinate
     */
    public void setStartY(int startY) {
        this.startY = startY;
    }

    /**
     * Setter-Function to set ??? .
     *
     * @param lastRound -> ?
     */
    public void setLastRound(boolean lastRound) {
        this.lastRound = lastRound;
    }

    /**
     * Setter-Function to set ??? .
     *
     * @param nextRound -> ?
     */
    public void setNextRound(boolean nextRound) {
        this.nextRound = nextRound;
    }

    /**
     * Setter-Function for the x-coordinate of the BackupCopy from the robot.
     *
     * @param position -> Integer of the x-coordinate
     */
    public void setbackupCopyX(int position) {
        backupCopyX = position;
    }

    /**
     * Setter-Function for the y-coordinate of the BackupCopy from the robot.
     *
     * @param position -> Integer of the y-coordinate
     */
    public void setbackupCopyY(int position) {
        backupCopyY = position;
    }

    /**
     * Setter-Function from the status of the robot (destroyed or not destroyed).
     *
     * @param destroyed -> boolean that encoded the status
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * Increase the damagePoints of a robot by 1.
     */
    public void damageUp() {
        damagePoints += 1;
    }
    
    /**
     * Reset damagePoints (to 0).
     */
    public void damageReset() {
        damagePoints = 0;
    }

    /**
     * Decrease lifePoints by 1.
     */
    public void lifeDown() {
        lifePoints -= 1;
    }

    public void incCheckPointNumber() {
        checkPointNumber++;
    }

    /**
     * Function to repair the Robot (only 1 Point).
     */
    public void onRepairField() {
        damagePoints -= 1;
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
        int tileSize = (Gdx.graphics.getHeight() / board.fieldmatrix.length);
        int x = xcoor;
        int y = Math.abs(ycoor - (board.fieldmatrix.length - 1));
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
    
    /**
     * Function that creates a given number of robots.
     * @param numberRobots number of robots
     * @return Array of robots
     */
    public static Robot[] createRobots(int numberRobots) {
        Robot[] robots = new Robot[numberRobots];
        for (int i = 0; i < robots.length; i++) {
            robots[i] = new Robot();
        }
        return robots;
    }

    public static Robot[] getPlayers() {
        Robot[] playersCopy = players;
        return playersCopy;
    }

    /** Setter for players.
     * @param players2 setter for players
     */
    public static void setPlayers(Robot[] players2) {
        for (int i = 0; i < players.length; i++) {
            players[i] = players2[i]; 
        }
        
    }

    public LinkedList<Card> getSelectedCards() {
        return selectedCards;
    }
    
    public void resetList() {
        selectedCards = new LinkedList<Card>();       
    }
    
    public void addCard(Card card, int playerNumber) {
        card.setCardPlayerNumber(playerNumber);
        this.getSelectedCards().add(card);
    }

}
