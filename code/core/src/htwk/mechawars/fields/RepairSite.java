package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Robot;

/**
 * Class of a Repair Site.
 */
public class RepairSite extends Field {

    // attribut which indicates the type 
    // 1 = simple repairsite, 2 = double repairsite
    private int type;
    private Texture tile;
    

    /**
     * Constructor of a Repair Site.
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

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", type: " + this.type;
        return attributes;
    }

    /**
     * Take the Type of the Repair Site, either simple or double.
     * @return the Type
     */
    public int getType() {
        return this.type;
    }

    public Texture getTile() {
        return this.tile;
    }
    
    @Override
    public Robot action(Robot robot) {
        robot.setbackupCopyX(robot.getXcoor());
        robot.setbackupCopyY(robot.getYcoor());
        return robot;       
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        RepairSite other = (RepairSite) obj;
        if (xcoor != other.xcoor) return false;
        if (ycoor != other.ycoor) return false;
        if (type != other.type) return false;

        return true;
    }
}
