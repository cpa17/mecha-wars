package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Start Field.
 */
public class StartField extends Field {

    // Attribut was angibt welche Nummer das Startfeld hat
    // 1 = Startfeld Nr. 1, 2 = Startfeld Nr.2, ...
    private int number;
    private Texture tile;

    /**
     * Constructor of a Start Field.
     */
    public StartField(int xcoor, int ycoor, int number) {
        super(xcoor, ycoor);
        this.number = number; 
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "startfield/" + "StartField0" + String.valueOf(number) + ".png"));
    }

    @Override
    public String showAttributes() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", number: " + this.number;
        return attributes;
    }

    /**
     * Take the Number of the Start Field.
     * @return the Number
     */
    public int getNumber() {
        return this.number;
    }
    
    /**
     * Take the Texture of the tile
     * @return the tile
     */
    public Texture getTile() {
        return this.tile;
    }
}
