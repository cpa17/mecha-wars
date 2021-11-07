package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

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
        this.tile = new Texture("mapAssets/Checkpoint.png");
    }

    @Override
    public String showAttributes() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", number: " + this.number;
        return attributes;
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
}
