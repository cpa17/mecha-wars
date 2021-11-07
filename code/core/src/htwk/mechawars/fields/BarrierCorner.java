package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Class of a Corner Barrier.
 */
public class BarrierCorner extends Field {

    // Attribut was angibt in welcher Ecke sich die Barriere befindet
    // 1 = linksoben, 2 = rechtsoben, 3 = rechtsunten, 4 = linksunten
    private int corner;
    private Texture tile;

    /**
     * Constructor of a Corner Barrier.
     */
    public BarrierCorner(int xcoor, int ycoor, int corner) {
        super(xcoor, ycoor);
        this.corner = corner;
        this.tile = new Texture(Gdx.files.internal( "mapAssets/" + "barriercorner/" + "BarrierCorner0" + String.valueOf(corner) + ".png"));
    }

    @Override
    public String showAttributes() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", corner: " + this.corner;
        return attributes;
    }

    /**
     * Take the Corner where the Barrier is.
     * @return the Corner
     */
    public int getCorner() {
        return this.corner;
    }
    
    /**
     * Take the Texture of the tile
     * @return the tile
     */
    public Texture getTile() {
        return this.tile;
    }
}
