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
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "conveyorbelt/"
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
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "conveyorbelt/"
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
        if (robot.getLastField() instanceof ConveyorBelt ||
                robot.getLastField() instanceof ExpressConveyorBelt) {
            switch (this.end) {
                case 1 :
                    while (robot.getDir() != Dir.WEST) {
                        robot.turn((byte) 3);
                    }
                    break;

                case 2 :
                    while (robot.getDir() != Dir.NORTH) {
                        robot.turn((byte) 3);
                    }
                    break;

                case 3 :
                    while (robot.getDir() != Dir.EAST) {
                        robot.turn((byte) 3);
                    }
                    break;

                case 4 :
                    while (robot.getDir() != Dir.SOUTH) {
                        robot.turn((byte) 3);
                    }
                    break;

                default : break;
            }
        }
        robot.moveInDirectionByField((byte) 1);
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
