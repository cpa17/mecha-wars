package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Corner Barrier.
 */
public class BarrierCorner extends Field {

    // attribute which shows where the coner of the barrier is located
    // 1 = top-left, 2 = top-right, 3 = bottom-right, 4 = bottom-left
    private int corner;
    private Texture tile;

    /**
     * Constructor of a Corner Barrier.
     */
    public BarrierCorner(int xcoor, int ycoor, int corner) {
        super(xcoor, ycoor);
        this.corner = corner;
        this.tile = 
                new Texture(Gdx.files.internal("mapAssets/" 
        + "barriercorner/" + "BarrierCorner0" + String.valueOf(corner) + ".png"));
    }

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", corner: " + this.corner;
        return attributes;
    }

    /**
     * Takes the Corner where the Barrier is.
     * @return the Corner
     */
    public int getCorner() {
        return this.corner;
    }

    @Override
    public Texture getTile() {
        return this.tile;
    }
}
