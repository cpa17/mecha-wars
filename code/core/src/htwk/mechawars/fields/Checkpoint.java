package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Checkpoint.
 */
public class Checkpoint extends Field {

    // Attribut was angibt um den wievielten Checkpoint es sich handelt
    // 0 = Checkpoint ohne Nummer, 1 = 1. Checkpoint, 2 = 2. Checkpoint, 3 = 3. Checkpoint, 4 = 4. Checkpoint, 5 = 5. Checkpoint
    private int number;
    private Texture tile;

    /**
     * Constructor of a Checkpoint.
     */
    public Checkpoint(int xcoor, int ycoor, int number) {
        super(xcoor, ycoor);
        this.number = number;
        this.tile = new Texture("mapAssets/Checkpoint.png");
    }

    @Override
    public String showAttributes(){
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor + ", number: " + this.number;
        return attributes;
    }

    /**
     * Take the Number of the Checkpoint.
     * @return the Number
     */
    public int getNumber(){
        return this.number;
    }
    
    public Texture getTile(){
        return this.tile;
    }
}
