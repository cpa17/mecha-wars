package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Corner Barrier.
 */
public class BarrierCorner extends Field {

    // attribute which shows where the corner of the barrier is located
    // 1 = top-left, 2 = top-right, 3 = bottom-right, 4 = bottom-left
    private int corner;
    private Texture tile;

    /**
     * Constructor of a Corner Barrier.
     */
    public BarrierCorner(int xcoor, int ycoor, int corner) {
        super(xcoor, ycoor);
        this.corner = corner;
        this.tile = new Texture(Gdx.files.internal("mapAssets/"
                + "barriercorner/" + "BarrierCorner0" + String.valueOf(corner) + ".png"));
    }

    /**
     * Constructor of a Corner Barrier which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public BarrierCorner(int xcoor, int ycoor, int corner, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.corner = corner;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "barriercorner/"
                    + "BarrierCorner0" + String.valueOf(corner) + ".png"));
        }
    }

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", corner: " + this.corner;
        return attributes;
    }

    public int getCorner() {
        return this.corner;
    }

    @Override
    public Texture getTile() {
        return this.tile;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        BarrierCorner other = (BarrierCorner) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
            return false;
        }
        if (corner != other.corner) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        assert false : "hashCode not designed";
        return 42;
    }
}
