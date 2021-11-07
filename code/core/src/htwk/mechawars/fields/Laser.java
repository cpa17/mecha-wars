package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Laser.
 */
public class Laser extends Field {

    // Attribut was angibt um welche Art Laser es sich andelt
    // 0 = Beginn links, 1 = Beginn oben, 2 = Beginn rechts, 3 = Beginn unten,
    // 4 = Mittelteil horizontal, 5 = Mittelteil vertikal,
    // 6 = Ende links, 7 = Ende oben, 8 = Ende rechts, 9 = Ende unten
    private int type;
    private Texture tile;

    /**
     * Constructor of a Laser.
     */
    public Laser(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
        this.type = type;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "laser/" + "Laser0" + String.valueOf(type) + ".png"));
    }

    @Override
    public String showAttributes() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", type: " + this.type;
        return attributes;
    }

    /**
     * Take what Type of laser piece it is.
     * @return the Type
     */
    public int getType() {
        return this.type;
    }
    
    /**
     * Take the Texture of the tile
     * @return the tile
     */
    public Texture getTile() {
        return this.tile;
    }
}
