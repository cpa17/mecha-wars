package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Black Hole.
 */
public class BlackHole extends Field {

    private Texture tile;

    /**
     * Constructor of a Black Hole.
     */
    public BlackHole(int xcoor, int ycoor) {
        super(xcoor, ycoor);
        this.tile = new Texture("mapAssets/BlackHole.png");
    }
    
    /**
     * Take the Texture of the tile
     * @return the tile
     */
    public Texture getTile() {
        return this.tile;
    }
}
