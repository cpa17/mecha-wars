package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;

/**
 * Class of a Conveyor Belt.
 */
public class ConveyorBelt extends Field {

    // attribute which represents the start and the end of a conveyorbelt
    // 1 = left, 2 = top, 3 = right, 4 = bottom, 5 = left and right, 6 = top and bottom,
    // 7 = right and top, 8 = left and top, 9 = right and bottom, 0 = left and bottom
    private int start;
    private int end;
    private Texture tile;

    /**
     * Constructor of a Conveyor Belt.
     */
    public ConveyorBelt(int xcoor, int ycoor, int start, int end) {
        super(xcoor, ycoor);
        this.start = start;
        this.end = end;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "conveyorBelt/"
                + "ConveyorBelt" + String.valueOf(start) + String.valueOf(end) + ".png"));
    }

    /**
     * Constructor of a Conveyor Belt which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public ConveyorBelt(int xcoor, int ycoor, int start, int end, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.start = start;
        this.end = end;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "conveyorBelt/"
                    + "ConveyorBelt" + String.valueOf(start) + String.valueOf(end) + ".png"));
        }
    }

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", start: " + this.start + ", end: " + this.end;
        return attributes;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public Texture getTile() {
        return this.tile;
    }
    
    @Override
    public Robot turnAction(Robot robot) {

        switch (this.end) {
            case 1:
                if (robot.getLastMovementByConveyor()) {
                    if (this.start == 2) {
                        robot.turn((byte) 3);
                    }
                    if (this.start == 4) {
                        robot.turn((byte) 1);
                    }
                    if (this.start == 6) {
                        if (robot.getLastConveyorField().getYcoor() > this.ycoor) {
                            robot.turn((byte) 3);
                        }
                        if (robot.getLastConveyorField().getYcoor() < this.ycoor) {
                            robot.turn((byte) 1);
                        }
                    }
                    if (this.start == 7) {
                        if (robot.getLastConveyorField().getYcoor() < this.ycoor) {
                            robot.turn((byte) 1);
                        }
                    }
                    if (this.start == 9) {
                        if (robot.getLastConveyorField().getYcoor() > this.ycoor) {
                            robot.turn((byte) 3);
                        }
                    }
                }
                robot.moveInDirectionByField((byte) 1, Dir.WEST);
                break;

            case 2:
                robot.moveInDirectionByField((byte) 1, Dir.NORTH);
                break;

            case 3:
                robot.moveInDirectionByField((byte) 1, Dir.EAST);
                break;

            case 4:
                robot.moveInDirectionByField((byte) 1, Dir.SOUTH);
                break;

            default:
                break;
        }
        robot.setLastMovementByConveyor(true);
        robot.setLastConveyorField(this);

        return robot;     
    }
    
    @Override
    public Robot cardAction(Robot robot) {
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

        ConveyorBelt other = (ConveyorBelt) obj;
        if (xcoor != other.xcoor) {
            return false;
        }
        if (ycoor != other.ycoor) {
            return false;
        }
        if (start != other.start) {
            return false;
        }
        if (end != other.end) {
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
