package htwk.mechawars.fields;

/**
 * Class of a Repair Site.
 */
public class RepairSite extends Field {

    // Attribut was angibt um welche Art Reparaturfeld es sich handelt
    // 1 = einfaches Reparaturfeld, 2 = Doppeltes Reparaturfeld
    private int type;

    /**
     * Constructor of a Repair Site.
     */
    public RepairSite(int xcoor, int ycoor, int type) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.type = type;
    }

    /**
     * Take the Type of the Repair Site, either simple or double.
     * @return the Type
     */
    public int getType(){
        return this.type;
    }
}
