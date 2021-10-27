package htwk.mechawars.fields;

public class RepairSite extends Field {

    // Attribut was angibt um welche Art Reparaturfeld es sich handelt
    // 1 = einfaches Reparaturfeld, 2 = Doppeltes Reparaturfeld
    private int type;

    /**
     * Constructor of a Repair Site
     */
    public RepairSite(int xcoor, int ycoor, int type) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.type = type;
    }

    public int getType(){
        return this.type;
    }
}
