package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Blockade.
 */
public class Blockade extends Field {

    // attribute which represents the type of the blockade
    // 1 = horizontal with the numbers 2 and 4, 2 = horizontal with the numbers 1, 3 and 5
    // 3 = vertical with the numbers 2 and 4, 4 = vertical with the numbers 1, 3 and 5
    private int type;
    private Texture tile;

    /**
     * Constructor of a Blockade.
     */
    public Blockade(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
        this.type = type;
        this.tile =
                new Texture(Gdx.files.internal("mapAssets/" 
        + "blockade/" + "Blockade0" + String.valueOf(type) + ".png"));
    }

    @Override
    public String showAttributes() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", type: " + this.type;
        return attributes;
    }

    /**
     * Take the Type of the Blockade.
     * @return the Type
     */
    public int getType() {
        return this.type;
    }

    public Texture getTile() {
        return this.tile;
    }
}
