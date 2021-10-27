package htwk.mechawars.fields;

/**
 * Class of a Blockade.
 */
public class Blockade extends Field {

    // Attribut was angibt um welche der beiden Blockaden es sich handelt
    // 1 = Blockade mit Ziffern 2 und 4, 2 = Blockade mit Ziffern 1, 3 und 5
    private int type;

    /**
     * Constructor of a Blockade.
     */
    public Blockade(int xcoor, int ycoor, int type) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.type = type;
    }

    /**
     * Take the Type of the Blockade.
     * @return the Type
     */
    public int getType(){
        return this.type;
    }
}
