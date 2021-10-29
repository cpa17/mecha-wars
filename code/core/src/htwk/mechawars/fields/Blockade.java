package htwk.mechawars.fields;

/**
 * Class of a Blockade.
 */
public class Blockade extends Field {

    // Attribut was angibt um welche der beiden Blockaden es sich handelt und ob sie horizontal oder vertikal sind
    // 1 = horizontale Blockade mit Ziffern 2 und 4, 2 = horizontale Blockade mit Ziffern 1, 3 und 5
    // 3 = vertikale Blockade mit Ziffern 2 und 4, 4 = vertikale Blockade mit Ziffern 1, 3 und 5
    private int type;

    /**
     * Constructor of a Blockade.
     */
    public Blockade(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
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
