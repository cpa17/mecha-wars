package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Start Field.
 */
public class StartField extends Field {

    // attribute which indicates the number of a certain start field
    private int number;
    private Texture tile;

    /**
     * Constructor of a Start Field.
     */
    public StartField(int xcoor, int ycoor, int number) {
        super(xcoor, ycoor);
        this.number = number; 
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "startfield/"
                + "StartField0" + String.valueOf(number) + ".png"));
    }

    /**
     * Constructor of a Start Field which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public StartField(int xcoor, int ycoor, int number, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.number = number;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "startfield/"
                    + "StartField0" + String.valueOf(number) + ".png"));
        }
    }

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", number: " + this.number;
        return attributes;
    }

    public int getNumber() {
        return this.number;
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

        StartField other = (StartField) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
            return false;
        }
        if (number != other.number) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        assert false : "hashCode not designed";
        return 42;
    }
}
