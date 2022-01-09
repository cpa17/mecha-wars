package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import htwk.mechawars.board.Dir;
import htwk.mechawars.board.Robot;

/**
 * Class of a Express Conveyor Belt.
 */
public class ExpressConveyorBelt extends Field {

    // attribute which represents the start and the end of an express conveyorbelt
    // 1 = left, 2 = top, 3 = right, 4 = bottom, 5 = left and right, 6 = top and bottom,
    // 7 = right and top, 8 = left and top, 9 = right and bottom, 0 = left and bottom
    private int start;
    private int end;
    private Texture tile;

    /**
     * Constructor of a Express Conveyor Belt with no lasers or barriers.
     */
    public ExpressConveyorBelt(int xcoor, int ycoor, int start, int end) {
        super(xcoor, ycoor);
        this.start = start;
        this.end = end;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "expressconveyorBelt/"
                + "ExpressConveyorBelt" + String.valueOf(start) + String.valueOf(end) + ".png"));
    }

    /**
     * Constructor of a Express Conveyor Belt with no lasers or barriers
     * which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public ExpressConveyorBelt(int xcoor, int ycoor, int start, int end, boolean isTest) {
        super(xcoor, ycoor, isTest);
        this.start = start;
        this.end = end;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "expressconveyorBelt/"
                    + "ExpressConveyorBelt" + String.valueOf(start) + String.valueOf(end)
                    + ".png"));
        }
    }

    /**
     * Constructor of a Express Conveyor Belt with barrier- and laser-attributes.
     */
    public ExpressConveyorBelt(int xcoor, int ycoor, int start, int end, int laserVertical,
                               int laserHorizontal, boolean barrierLeft, boolean barrierTop,
                               boolean barrierRight, boolean barrierBottom) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom);

        this.start = start;
        this.end = end;
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "expressconveyorBelt/"
                + "ExpressConveyorBelt" + String.valueOf(start) + String.valueOf(end) + ".png"));
    }

    /**
     * Constructor of a Express Conveyor Belt with barrier- and laser-attributes
     * which can skip creating the assets.
     *
     * @param isTest indicates that this is a test
     */
    public ExpressConveyorBelt(int xcoor, int ycoor, int start, int end, int laserVertical,
                               int laserHorizontal, boolean barrierLeft, boolean barrierTop,
                               boolean barrierRight, boolean barrierBottom, boolean isTest) {

        super(xcoor, ycoor, laserVertical, laserHorizontal,
                barrierLeft, barrierTop, barrierRight, barrierBottom, isTest);

        this.start = start;
        this.end = end;
        if (!isTest) {
            this.tile = new Texture(Gdx.files.internal("mapAssets/" + "expressconveyorBelt/"
                    + "ExpressConveyorBelt" + String.valueOf(start) + String.valueOf(end)
                    + ".png"));
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
    public Robot cardAction(Robot robot) {
        switch (this.end) {
            // express conveyor belt runs to the left
            case 1:
                // if the robot was pushed to its current position by a express conveyor belt,
                // then the express conveyor belt could rotate the robot, otherwise not
                if (robot.getLastMovementByConveyor()) {
                    // express conveyor belt starts at the top
                    if (this.start == 2) {
                        robot.turn((byte) 1);
                    }
                    // express conveyor belt starts at the bottom
                    if (this.start == 4) {
                        robot.turn((byte) 3);
                    }
                    // express conveyor belt starts at the bottom and the top
                    if (this.start == 6) {
                        // robot comes from the bottom
                        if (robot.getLastConveyorField().getYcoor() > this.ycoor) {
                            robot.turn((byte) 3);
                        }
                        // robot comes from the top
                        if (robot.getLastConveyorField().getYcoor() < this.ycoor) {
                            robot.turn((byte) 1);
                        }
                    }
                    // express conveyor belt starts at the right and the top
                    if (this.start == 7) {
                        // robot comes from the top
                        if (robot.getLastConveyorField().getYcoor() < this.ycoor) {
                            robot.turn((byte) 1);
                        }
                    }
                    // express conveyor belt starts at the right and the bottom
                    if (this.start == 9) {
                        // robot comes from the bottom
                        if (robot.getLastConveyorField().getYcoor() > this.ycoor) {
                            robot.turn((byte) 3);
                        }
                    }
                }
                robot.moveInDirectionByField((byte) 1, Dir.WEST);
                break;

            // express conveyor belt runs to the top
            case 2:
                // if the robot was pushed to its current position by a express conveyor belt,
                // then the express conveyor belt could rotate the robot, otherwise not
                if (robot.getLastMovementByConveyor()) {
                    // express conveyor belt starts at the left and the bottom
                    if (this.start == 0) {
                        // robot comes from the left
                        if (robot.getLastConveyorField().getXcoor() < this.xcoor) {
                            robot.turn((byte) 3);
                        }
                    }
                    // express conveyor belt starts at the left
                    if (this.start == 1) {
                        robot.turn((byte) 3);
                    }
                    // express conveyor belt starts at the right
                    if (this.start == 3) {
                        robot.turn((byte) 1);
                    }
                    // express conveyor belt starts at the left and the right
                    if (this.start == 5) {
                        // robot comes from the right
                        if (robot.getLastConveyorField().getXcoor() > this.xcoor) {
                            robot.turn((byte) 1);
                        }
                        // robot comes from the left
                        if (robot.getLastConveyorField().getXcoor() < this.xcoor) {
                            robot.turn((byte) 3);
                        }
                    }
                    // express conveyor belt starts at the right and the bottom
                    if (this.start == 9) {
                        // robot comes from the right
                        if (robot.getLastConveyorField().getXcoor() > this.xcoor) {
                            robot.turn((byte) 1);
                        }
                    }
                }
                robot.moveInDirectionByField((byte) 1, Dir.NORTH);
                break;

            // express conveyor belt runs to the right
            case 3:
                // if the robot was pushed to its current position by a express conveyor belt,
                // then the express conveyor belt could rotate the robot, otherwise not
                if (robot.getLastMovementByConveyor()) {
                    // express conveyor belt starts at the left and the bottom
                    if (this.start == 0) {
                        // robot comes from the bottom
                        if (robot.getLastConveyorField().getYcoor() > this.ycoor) {
                            robot.turn((byte) 1);
                        }
                    }
                    // express conveyor belt starts at the top
                    if (this.start == 2) {
                        robot.turn((byte) 3);
                    }
                    // express conveyor belt starts at the bottom
                    if (this.start == 4) {
                        robot.turn((byte) 1);
                    }
                    // express conveyor belt starts at the top and the bottom
                    if (this.start == 6) {
                        // robot comes from the top
                        if (robot.getLastConveyorField().getYcoor() < this.ycoor) {
                            robot.turn((byte) 3);
                        }
                        // robot comes from the bottom
                        if (robot.getLastConveyorField().getYcoor() > this.ycoor) {
                            robot.turn((byte) 1);
                        }
                    }
                    // express conveyor belt starts at the left and the top
                    if (this.start == 8) {
                        // robot comes from the top
                        if (robot.getLastConveyorField().getYcoor() < this.ycoor) {
                            robot.turn((byte) 3);
                        }
                    }
                }
                robot.moveInDirectionByField((byte) 1, Dir.EAST);
                break;

            // express conveyor belt runs to the bottom
            case 4:
                // if the robot was pushed to its current position by a express conveyor belt,
                // then the express conveyor belt could rotate the robot, otherwise not
                if (robot.getLastMovementByConveyor()) {
                    // express conveyor belt starts at the left
                    if (this.start == 1) {
                        robot.turn((byte) 1);
                    }
                    // express conveyor belt starts at the right
                    if (this.start == 3) {
                        robot.turn((byte) 3);
                    }
                    // express conveyor belt starts at the left and the right
                    if (this.start == 5) {
                        // robot comes from the right
                        if (robot.getLastConveyorField().getXcoor() > this.xcoor) {
                            robot.turn((byte) 3);
                        }
                        // robot comes from the left
                        if (robot.getLastConveyorField().getXcoor() < this.xcoor) {
                            robot.turn((byte) 1);
                        }
                    }
                    // express conveyor belt starts at the right and the top
                    if (this.start == 7) {
                        // robot comes from the right
                        if (robot.getLastConveyorField().getXcoor() > this.xcoor) {
                            robot.turn((byte) 3);
                        }
                    }
                    // express conveyor belt starts at the left and the top
                    if (this.start == 8) {
                        // robot comes from the left
                        if (robot.getLastConveyorField().getXcoor() < this.xcoor) {
                            robot.turn((byte) 1);
                        }
                    }
                }
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

        ExpressConveyorBelt other = (ExpressConveyorBelt) obj;
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
