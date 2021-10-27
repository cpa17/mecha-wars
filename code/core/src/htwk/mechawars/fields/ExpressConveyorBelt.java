package htwk.mechawars.fields;

public class ExpressConveyorBelt extends Field {

    // Attribute die angeben aus welcher Richtung das Express-Förderband kommt und in welche Richtung es führt
    // 1 = links, 2 = oben, 3 = rechts, 4 = unten, 5 = links und rechts, 6 = oben und unten
    private int start;
    private int end;

    /**
     * Constructor of a Express Conveyor Belt
     */
    public ExpressConveyorBelt(int xcoor, int ycoor, int start, int end) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.start = start;
        this.end = end;
    }

    public int getStart(){
        return this.start;
    }

    public int getEnd(){
        return this.end;
    }
}
