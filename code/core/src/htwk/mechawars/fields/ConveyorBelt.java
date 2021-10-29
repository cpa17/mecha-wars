package htwk.mechawars.fields;

/**
 * Class of a Conveyor Belt.
 */
public class ConveyorBelt extends Field {

    // Attribute die angeben aus welcher Richtung das Förderband kommt und in welche Richtung es führt
    // 1 = links, 2 = oben, 3 = rechts, 4 = unten, 5 = links und rechts, 6 = oben und unten,
    // 7 = oben und rechts, 8 = oben und links, 9 = unten und rechts, 0 = unten und links
    private int start;
    private int end;

    /**
     * Constructor of a Conveyor Belt.
     */
    public ConveyorBelt(int xcoor, int ycoor, int start, int end) {
        super(xcoor, ycoor);
        this.start = start;
        this.end = end;
    }

    /**
     * Take from where the Conveyor Belt come from.
     * @return the Start
     */
    public int getStart(){
        return this.start;
    }

    /**
     * Take where the Conveyor Belt goes.
     * @return the End
     */
    public int getEnd(){
        return this.end;
    }
}
