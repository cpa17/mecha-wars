package htwk.mechawars.fields;

/**
 * Class of a Repair Site.
 */
public class RepairSite extends Field {

    // Attribut was angibt um welche Art Reparaturfeld es sich handelt
    // 1 = einfaches Reparaturfeld, 2 = doppeltes Reparaturfeld
    private int type;

    /**
     * Constructor of a Repair Site.
     */
    public RepairSite(int xcoor, int ycoor, int type) {
        super(xcoor, ycoor);
        this.type = type;
    }

    @Override
    public String showAttributes(){
        String attributes = "xcoor: " + this.xcoor + "ycoor: " + this.ycoor + "type: " + this.type;
        return attributes;
    }

    /**
     * Take the Type of the Repair Site, either simple or double.
     * @return the Type
     */
    public int getType(){
        return this.type;
    }
}
