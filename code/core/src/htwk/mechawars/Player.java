package htwk.mechawars;

public class Player {
    private int lifePoints;
    private int damagePoints;
    private boolean shutDown;
  
    public Player() {
        
    }
    
    void setDP() {
        damagePoints += 1;
    }
    
    void setLP(int damage) {
        lifePoints -= damage;
    }
    
    void setSD() {
        shutDown = true;
    }
    
    int getDP() {
        return damagePoints;
    }
    
    int getLP() {
        return lifePoints;
    }
    
    boolean getSD() {
        return shutDown;
    }  
}
