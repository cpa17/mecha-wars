package htwk.mechawars.fields;

/**
 * Class of a Corner Barrier.
 */
public class BarrierCorner extends Field{

    // Attribut was angibt in welcher Ecke sich die Barriere befindet
    // 1 = linksoben, 2 = rechtsoben, 3 = rechtsunten, 4 = linksunten
    private int corner;

    /**
     * Constructor of a Corner Barrier.
     */
    public BarrierCorner(int xcoor, int ycoor, int corner) {
        super(xcoor, ycoor);
        this.corner = corner;
    }

    /**
     * Take the Corner where the Barrier is.
     * @return the Corner
     */
    public int getCorner(){
        return this.corner;
    }
}
