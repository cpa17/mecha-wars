package htwk.mechawars.fields;

public class BarrierCorner extends Field{

    // Attribut was angibt in welcher Ecke sich die Barriere befindet
    // 1 = linksoben, 2 = rechtsoben, 3 = rechtsunten, 4 = linksunten
    private int corner;

    /**
     * Constructor of a Corner Barrier
     */
    public BarrierCorner(int xcoor, int ycoor, int corner) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.corner = corner;
    }

    public int getCorner(){
        return this.corner;
    }
}
