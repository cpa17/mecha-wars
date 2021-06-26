package htwk.mechawars;

/**
 * Class that represents the Player.
 */
public class Player {
    private int lifePoints;
    private int damagePoints;
    private boolean shutDown;
  
    public Player() {
        
    }
    
    void setDp() {
        damagePoints += 1;
    }
    
    void setLp(int damage) {
        lifePoints -= damage;
    }
    
    void setSd() {
        shutDown = true;
    }
    
    int getDp() {
        return damagePoints;
    }
    
    int getLp() {
        return lifePoints;
    }
    
    boolean getSd() {
        return shutDown;
    }  
}
