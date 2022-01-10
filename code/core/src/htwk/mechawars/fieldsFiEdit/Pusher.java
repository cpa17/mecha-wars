package htwk.mechawars.fieldsFiEdit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Pusher.
 */
public class Pusher extends Field {

    // attribute which represents the type of the pusher
    // 1 = horizontal with the numbers 2 and 4, 2 = horizontal with the numbers 1, 3 and 5
    // 3 = vertical with the numbers 2 and 4, 4 = vertical with the numbers 1, 3 and 5
    private int type;
    private Texture tile;

    /**
     * Constructor of a Pusher.
     */
    public Pusher(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
        this.type = type;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "pusher/"
                + "Pusher0" + String.valueOf(type) + ".png"));
    }

    /**
     * Constructor of a Pusher which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public Pusher(int xcoor, int ycoor, int type, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.type = type;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "pusher/"
                    + "Pusher0" + String.valueOf(type) + ".png"));
        }
    }  
    
    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", type: " + this.type;
        return attributes;
    }

    public int getType() {
        return this.type;
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

        Pusher other = (Pusher) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
            return false;
        }
        if (type != other.type) {
            return false;
        }

        return true;
    }

    // Function that overwrites the hash code but has no further meaning or functionality
    // It only has to exist for the pipeline to work with the overridden equals function
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42;
    }
}
