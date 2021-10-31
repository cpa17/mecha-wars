package htwk.mechawars.fields;

/**
 * Class of a Side Barrier.
 */
public class BarrierSide extends Field {

    // Attribut was angibt auf welcher Seite sich die Barriere befindet
    // 1 = links, 2 = oben, 3 = rechts, 4 = unten
    private int site;

    /**
     * Constructor of a Side Barrier.
     */
    public BarrierSide(int xcoor, int ycoor, int site) {
        super(xcoor, ycoor);
        this.site = site;
    }

    @Override
    public String showAttributes(){
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor + ", site: " + this.site;
        return attributes;
    }

    /**
     * Take the Side where the Barrier is.
     * @return the Side
     */
    public int getSite(){
        return this.site;
    }
}
