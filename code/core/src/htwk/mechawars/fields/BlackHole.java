package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Robot;

/**
 * Class of a Black Hole.
 */
public class BlackHole extends Field {

    private Texture tile;

    /**
     * Constructor of a Black Hole with no lasers or barriers.
     */
    public BlackHole(int xcoor, int ycoor) {
        super(xcoor, ycoor);
        this.tile = new Texture("mapAssets/BlackHole.png");
    }

    /**
     * Constructor of a Black Hole with no lasers or barriers which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public BlackHole(int xcoor, int ycoor, boolean isTest) {
        super(xcoor, ycoor, isTest);
        if (!isTest) {
            this.tile = new Texture("mapAssets/BlackHole.png");
        }
    }

    /**
     * Constructor of a Black Hole with barrier- and laser-attributes.
     */
    public BlackHole(int xcoor, int ycoor, int laserVertical, int laserHorizontal,
                     boolean barrierLeft, boolean barrierTop, boolean barrierRight,
                     boolean barrierBottom) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom);

        this.tile = new Texture("mapAssets/BlackHole.png");
    }

    /**
     * Constructor of a Black Hole with barrier- and laser-attributes
     * which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public BlackHole(int xcoor, int ycoor, int laserVertical, int laserHorizontal,
                     boolean barrierLeft, boolean barrierTop, boolean barrierRight,
                     boolean barrierBottom, boolean isTest) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom, isTest);

        if (!isTest) {
            this.tile = new Texture("mapAssets/BlackHole.png");
        }
    }

    public Texture getTile() {
        return this.tile;
    }
    
    @Override
    public Robot cardAction(Robot robot) {
        robot.setDamage(10);
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

        BlackHole other = (BlackHole) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
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
