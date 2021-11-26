package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
        this.tile = 
                new Texture(Gdx.files.internal("mapAssets/" + 
        "conveyorBelt/" + "ConveyorBelt" + String.valueOf(start) + String.valueOf(end) + ".png"));
    }

    @Override
    public String toString() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", start: " + this.start + ", end: " + this.end;
        return attributes;
    }

    /**
     * Take from where the Conveyor Belt come from.
     * @return the Start
     */
    public int getStart() {
        return this.start;
    }

    /**
     * Take where the Conveyor Belt goes.
     * @return the End
     */
    public int getEnd() {
        return this.end;
    }

    public Texture getTile() {
        return this.tile;
    }
}
