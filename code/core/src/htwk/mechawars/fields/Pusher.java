package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;

/**
 * Class of a Pusher.
 */
public class Pusher extends Field {

    // attribute which represents the type of the pusher
    // 1 = horizontal with the numbers 2 and 4, 2 = horizontal with the numbers 1, 3 and 5
    // 3 = vertical with the numbers 2 and 4, 4 = vertical with the numbers 1, 3 and 5
    final int type;
    private Texture tile;

    /**
     * Constructor of a Pusher with no lasers or barriers.
     */
    public Pusher(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
        this.type = type;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "pusher/"
                + "Pusher0" + String.valueOf(type) + ".png"));
    }

    /**
     * Constructor of a Pusher with no lasers or barriers which can skip creating the assets.
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

    /**
     * Constructor of a Pusher with barrier- and laser-attributes.
     */
    public Pusher(int xcoor, int ycoor, int type, int laserVertical, int laserHorizontal,
                     boolean barrierLeft, boolean barrierTop, boolean barrierRight,
                     boolean barrierBottom) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom);
        this.type = type;

        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "pusher/"
                + "Pusher0" + String.valueOf(type) + ".png"));
    }

    /**
     * Constructor of a Pusher with barrier- and laser-attributes
     * which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public Pusher(int xcoor, int ycoor, int type, int laserVertical, int laserHorizontal,
                  boolean barrierLeft, boolean barrierTop, boolean barrierRight,
                  boolean barrierBottom, boolean isTest) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom, isTest);
        this.type = type;

        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "pusher/"
                    + "Pusher0" + String.valueOf(type) + ".png"));
        }
    }
    
    @Override
    public String toString() {
        String attributes = "";
        attributes = attributes + "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", type: " + this.type;

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

    public int getType() {
        return this.type;
    }

    public Texture getTile() {
        return this.tile;
    }

    @Override
    public Robot cardAction(Robot robot) {
        if (type == 1) {
            robot.moveInDirectionByField((byte) -1, robot.getDir());
        } else if (type == 3) {
            while (robot.getDir() != Dir.EAST) {
                robot.turn((byte) 3); 
            }
            robot.moveInDirectionByField((byte) 1, robot.getDir());
        }
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
