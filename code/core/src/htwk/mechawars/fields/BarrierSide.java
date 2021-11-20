package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Side Barrier.
 */
public class BarrierSide extends Field {

    // attribute which locates the side of the barrier
    // 1 = left, 2 = top, 3 = right, 4 = bottom
    private int side;
    private Texture tile;

    /**
     * Constructor of a Side Barrier.
     */
    public BarrierSide(int xcoor, int ycoor, int side) {
        super(xcoor, ycoor);
        this.side = side;
        this.tile = 
                new Texture(Gdx.files.internal("mapAssets/" + 
        "barrierside/" + "BarrierSide" + String.valueOf(side) + ".png"));
    }

    public BarrierSide(int xcoor, int ycoor, int side, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.side = side;
        if (!isTest) {
            this.tile =
                    new Texture(Gdx.files.internal("mapAssets/" +
                            "barrierside/" + "BarrierSide" + String.valueOf(side) + ".png"));
        }
    }

    @Override
    public String toString() {
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

    public Texture getTile() {
        return this.tile;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        BarrierSide other = (BarrierSide) obj;
        if (xcoor != other.xcoor) return false;
        if (ycoor != other.ycoor) return false;
        if (side != other.side) return false;

        return true;
    }
}
