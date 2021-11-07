package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Side Barrier.
 */
public class BarrierSide extends Field {

    // Attribut was angibt auf welcher Seite sich die Barriere befindet
    // 1 = links, 2 = oben, 3 = rechts, 4 = unten
    private int side;
    private Texture tile;

    /**
     * Constructor of a Side Barrier.
     */
    public BarrierSide(int xcoor, int ycoor, int side) {
        super(xcoor, ycoor);
        this.side = side;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "barrierside/" + "BarrierSide" + String.valueOf(side) + ".png"));
    }

    @Override
    public String showAttributes() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", side: " + this.side;
        return attributes;
    }

    /**
     * Take the Side where the Barrier is.
     * @return the Side
     */
    public int getSide() {
        return this.side;
    }
    
    /**
     * Take the Texture of the tile
     * @return the tile
     */
    public Texture getTile() {
        return this.tile;
    }
}
