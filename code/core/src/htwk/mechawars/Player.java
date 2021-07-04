package htwk.mechawars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class that represents the Player.
 */
public class Player {
    private int lifePoints;
    private int damagePoints;
    private boolean shutDownMark;
    private Texture life;
    private Texture damage;
    private Texture shutDown;
    
    /**
     * Constructor of class Player.
     */
    public Player() {
        lifePoints = 0;
        damagePoints = 0;
        shutDownMark = false;
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
            case 0 :    life = new Texture(Gdx.files.internal("background1.png"));
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
            case 0 :    damage = new Texture(Gdx.files.internal("background5.png"));
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
            shutDown = new Texture(Gdx.files.internal("background15.png"));
        } else {
            shutDown = new Texture(Gdx.files.internal("background16.png"));
        }
    }
    
    /**
     * Draws the parameter textures. 
     */
    public void drawParameters(SpriteBatch batch) {
        updateLife();
        updateDamage();
        updateShutDown();
        batch.draw(life, 0, 0, 1280, 720);
        batch.draw(damage, 0, 0, 1280, 720);
        batch.draw(shutDown, 0, 0, 1280, 720);
    }
}
