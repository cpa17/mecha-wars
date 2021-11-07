package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Conveyor Belt.
 */
public class ConveyorBelt extends Field {

    // Attribute die angeben aus welcher Richtung das Förderband kommt und in welche Richtung es führt
    // 1 = links, 2 = oben, 3 = rechts, 4 = unten, 5 = links und rechts, 6 = oben und unten,
    // 7 = rechts und oben, 8 = links und oben, 9 = rechts und unten, 0 = links und unten
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
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "conveyorBelt/" + "ConveyorBelt" + String.valueOf(start) + String.valueOf(end) + ".png"));
     }

    @Override
    public String showAttributes() {
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
    
    /**
     * Take the Texture of the tile
     * @return the tile
     */
    public Texture getTile() {
        return this.tile;
    }
}
