package htwk.mechawars.fields;

/**
 * Class of a Gear.
 */
public class Gear extends Field {

    // Attribut was angibt in welche Richtung sich das Zahnrad dreht
    // 1 = rechtsrum, 2 = linksrum
    private int direction;

    /**
     * Constructor of a Gear.
     */
    public Gear(int xcoor, int ycoor, int direction) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.direction = direction;
    }

    /**
     * Take the Direction of the rotation of the Gear.
     * @return the Direction
     */
    public int getDirection(){
        return this.direction;
    }
}
