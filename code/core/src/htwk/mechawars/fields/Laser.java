package htwk.mechawars.fields;

/**
 * Class of a Laser.
 */
public class Laser extends Field {

    // Attribut was angibt um welche Art Laser es sich andelt
    // 0 = Beginn links, 1 = Beginn oben, 2 = Beginn rechts, 3 = Beginn unten,
    // 4 = Mittelteil horizontal, 5 = Mittelteil vertikal,
    // 6 = Ende links, 7 = Ende oben, 8 = Ende rechts, 9 = Ende unten
    private int type;

    /**
     * Constructor of a Laser.
     */
    public Laser(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
        this.type = type;
    }

    @Override
    public String showAttributes(){
        String attributes = "xcoor: " + this.xcoor + "ycoor: " + this.ycoor + "type: " + this.type;
        return attributes;
    }

    /**
     * Take what Type of laser piece it is.
     * @return the Type
     */
    public int getType(){
        return this.type;
    }
}
