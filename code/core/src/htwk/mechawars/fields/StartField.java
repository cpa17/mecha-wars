package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Robot;

/**
 * Class of a Start Field.
 */
public class StartField extends Field {

    // attribute which indicates the number of a certain start field
    private int number;
    private Texture tile;

    /**
     * Constructor of a Start Field with no lasers or barriers.
     */
    public StartField(int xcoor, int ycoor, int number) {
        super(xcoor, ycoor);
        this.number = number; 
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "startfield/"
                + "StartField0" + String.valueOf(number) + ".png"));
    }

    /**
     * Constructor of a Start Field with no lasers or barriers which can skip creating the assets.
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

    /**
     * Constructor of a Start Field with barrier- and laser-attributes.
     */
    public StartField(int xcoor, int ycoor, int number, int laserVertical, int laserHorizontal,
                     boolean barrierLeft, boolean barrierTop, boolean barrierRight,
                     boolean barrierBottom) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom);

        this.number = number;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "startfield/"
                + "StartField0" + String.valueOf(number) + ".png"));
    }

    /**
     * Constructor of a Start Field with barrier- and laser-attributes
     * which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public StartField(int xcoor, int ycoor, int number, int laserVertical, int laserHorizontal,
                      boolean barrierLeft, boolean barrierTop, boolean barrierRight,
                      boolean barrierBottom, boolean isTest) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom, isTest);

        this.number = number;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "startfield/"
                    + "StartField0" + String.valueOf(number) + ".png"));
        }
    }

    @Override
    public String toString() {
        String attributes = "";
        attributes = attributes + "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", number: " + this.number;

        if (this.laserVertical != 9) {
            attributes = attributes + ", laserVertical: " + this.laserVertical;
        }
        if (this.laserHorizontal != 9) {
            attributes = attributes + ", laserHorizontal: " + this.laserHorizontal;
        }
        if (this.barrierLeft) {
            attributes = attributes + ", barrierLeft: " + this.barrierLeft;
        }
        if (this.barrierTop) {
            attributes = attributes + ", barrierTop: " + this.barrierTop;
        }
        if (this.barrierRight) {
            attributes = attributes + ", barrierRight: " + this.barrierRight;
        }
        if (this.barrierBottom) {
            attributes = attributes + ", barrierBottom: " + this.barrierBottom;
        }

        return attributes;
    }

    public int getNumber() {
        return this.number;
    }

    public Texture getTile() {
        return this.tile;
    }
    
    @Override
    public Robot cardAction(Robot robot) {
        return robot;       
    }
    
    @Override
    public Robot turnAction(Robot robot) {
        return robot;       
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
        if (laserVertical != other.laserVertical) {
            return false;
        }
        if (laserHorizontal != other.laserHorizontal) {
            return false;
        }
        if (barrierLeft != other.barrierLeft) {
            return false;
        }
        if (barrierTop != other.barrierTop) {
            return false;
        }
        if (barrierRight != other.barrierRight) {
            return false;
        }
        if (barrierBottom != other.barrierBottom) {
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
