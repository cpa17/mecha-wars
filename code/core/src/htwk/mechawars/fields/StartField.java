package htwk.mechawars.fields;

/**
 * Class of a Start Field.
 */
public class StartField extends Field {

    // Attribut was angibt welche Nummer das Startfeld hat
    // 1 = Startfeld Nr. 1, 2 = Startfeld Nr.2, ...
    private int number;

    /**
     * Constructor of a Start Field.
     */
    public StartField(int xcoor, int ycoor, int number) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.number = number;
    }

    /**
     * Take the Number of the Start Field.
     * @return the Number
     */
    public int getNumber(){
        return this.number;
    }
}
