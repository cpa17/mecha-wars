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
     * Constructor of a Black Hole which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public BlackHole(int xcoor, int ycoor, boolean isTest) {
        super(xcoor, ycoor, isTest);
        if (!isTest) {
            this.tile = new Texture("mapAssets/BlackHole.png");
        }
    }

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

        BlackHole other = (BlackHole) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        assert false : "hashCode not designed";
        return 42;
    }
}
