package htwk.mechawars.fields;

public class StartField extends Field {

    // Attribut was angibt welche Nummer das Startfeld hat
    // 1 = Startfeld Nr. 1, 2 = Startfeld Nr.2, ...
    private int number;

    /**
     * Constructor of a Start Field
     */
    public StartField(int xcoor, int ycoor, int number) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.number = number;
    }

    public int getNumber(){
        return this.number;
    }
}
