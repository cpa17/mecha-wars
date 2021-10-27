package htwk.mechawars.fields;

public class Gear extends Field {

    // Attribut was angibt in welche Richtung sich das Zahnrad dreht
    // 1 = rechtsrum, 2 = linksrum
    private int direction;

    /**
     * Constructor of a Gear
     */
    public Gear(int xcoor, int ycoor, int direction) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.direction = direction;
    }

    public int getDirection(){
        return this.direction;
    }
}
