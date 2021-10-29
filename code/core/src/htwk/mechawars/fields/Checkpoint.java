package htwk.mechawars.fields;

/**
 * Class of a Checkpoint.
 */
public class Checkpoint extends Field {

    // Attribut was angibt um den wievielten Checkpoint es sich handelt
    // 0 = Checkpoint ohne Nummer, 1 = 1. Checkpoint, 2 = 2. Checkpoint, 3 = 3. Checkpoint, 4 = 4. Checkpoint
    private int number;

    /**
     * Constructor of a Checkpoint.
     */
    public Checkpoint(int xcoor, int ycoor, int number) {
        super(xcoor, ycoor);
        this.number = number;
    }

    /**
     * Take the Number of the Checkpoint.
     * @return the Number
     */
    public int getNumber(){
        return this.number;
    }
}
