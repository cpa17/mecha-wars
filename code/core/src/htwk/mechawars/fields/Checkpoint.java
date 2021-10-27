package htwk.mechawars.fields;

public class Checkpoint extends Field {

    // Attribut was angibt um den wievielten Checkpoint es sich handelt
    // 0 = Checkpoint ohne Nummer, 1 = 1. Checkpoint, 2 = 2. Checkpoint, 3 = 3. Checkpoint, 4 = 4. Checkpoint
    private int number;

    /**
     * Constructor of a Checkpoint
     */
    public Checkpoint(int xcoor, int ycoor, int number) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.number = number;
    }

    public int getNumber(){
        return this.number;
    }
}
