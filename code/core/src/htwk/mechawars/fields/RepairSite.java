package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Robot;

/**
 * Class of a Repair Site.
 */
public class RepairSite extends Field {

    // attribut which indicates the type 
    // 1 = simple repairsite, 2 = double repairsite
    final int type;
    private Texture tile;
    

    /**
     * Constructor of a Repair Site with no lasers or barriers.
     */
    public RepairSite(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
        this.type = type;
        
        if (type == 1) {
            this.tile = new Texture("mapAssets/repairsite/RepairSite01.png");
        } else {
            this.tile = new Texture("mapAssets/repairsite/RepairSite02.png");
        }
    }

    /**
     * Constructor of a Repair Site with no lasers or barriers which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public RepairSite(int xcoor, int ycoor, int type, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.type = type;
        if (!isTest) {
            if (type == 1) {
                this.tile = new Texture("mapAssets/repairsite/RepairSite01.png");
            } else {
                this.tile = new Texture("mapAssets/repairsite/RepairSite02.png");
            }
        }
    }

    /**
     * Constructor of a Repair Site with barrier- and laser-attributes.
     */
    public RepairSite(int xcoor, int ycoor, int type, int laserVertical, int laserHorizontal,
                     boolean barrierLeft, boolean barrierTop, boolean barrierRight,
                     boolean barrierBottom) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom);
        this.type = type;

        if (type == 1) {
            this.tile = new Texture("mapAssets/repairsite/RepairSite01.png");
        } else {
            this.tile = new Texture("mapAssets/repairsite/RepairSite02.png");
        }
    }

    /**
     * Constructor of a Repair Site with barrier- and laser-attributes
     * which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public RepairSite(int xcoor, int ycoor, int type, int laserVertical, int laserHorizontal,
                      boolean barrierLeft, boolean barrierTop, boolean barrierRight,
                      boolean barrierBottom, boolean isTest) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom, isTest);
        this.type = type;

        if (!isTest) {
            if (type == 1) {
                this.tile = new Texture("mapAssets/repairsite/RepairSite01.png");
            } else {
                this.tile = new Texture("mapAssets/repairsite/RepairSite02.png");
            }
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

    /**
     * Return the hole Tile.
     * 
     * @return a Texture.
     */
    public Texture getTile() {
        return this.tile;
    }
    
    @Override
    public Robot cardAction(Robot robot) {
        robot.setDamage(-1);
        
        robot.setbackupCopyX(robot.getXcoor());
        robot.setbackupCopyY(robot.getYcoor());
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

        RepairSite other = (RepairSite) obj;
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
