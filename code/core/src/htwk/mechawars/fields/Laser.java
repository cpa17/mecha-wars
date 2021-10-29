package htwk.mechawars.fields;

/**
 * Class of a Laser.
 */
public class Laser extends Field {

    // Attribut was angibt um welche Art Laser es sich andelt
    // 1 = Beginn links, 2 = Beginn oben, 3 = Beginn rechts, 4 = Beginn unten,
    // 5 = Mittelteil horizontal, 6 = Mittelteil vertikal,
    // 7 = Ende links, 8 = Ende oben, 9 = Ende rechts, 0 = Ende unten
    private int type;

    /**
     * Constructor of a Laser.
     */
    public Laser(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
        this.type = type;
    }

    /**
     * Take what Type of laser piece it is.
     * @return the Type
     */
    public int getType(){
        return this.type;
    }
}
