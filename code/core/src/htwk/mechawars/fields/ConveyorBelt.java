package htwk.mechawars.fields;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Conveyor Belt.
 */
public class ConveyorBelt extends Field {

    // Attribute die angeben aus welcher Richtung das Förderband kommt und in welche Richtung es führt
    // 1 = links, 2 = oben, 3 = rechts, 4 = unten, 5 = links und rechts, 6 = oben und unten,
    // 7 = rechts und oben, 8 = links und oben, 9 = rechts und unten, 0 = links und unten
    private int start;
    private int end;
    private Texture tile;

    /**
     * Constructor of a Conveyor Belt.
     */
    public ConveyorBelt(int xcoor, int ycoor, int start, int end) {
        super(xcoor, ycoor);
        this.start = start;
        this.end = end;
        
        //13.png -> von oben und unten nach rechts
        //14.png -> von unten nach rechts 
        //15.png -> von oben und rechts nach unten
        //16.png -> von unten nach oben
        
        switch(end){
        //nach links 
        case 1:
            switch(start) {
                //von oben
                case 2:
                    this.tile = new Texture("mapAssets/14.png");
                    break;
                //von rechts
                case 3:
                    this.tile = new Texture("mapAssets/16.png");
                    break; 
                //von unten
                case 4:
                    this.tile = new Texture("mapAssets/14.png");
                    break;
                //von oben und unten
                case 6:
                    this.tile = new Texture("mapAssets/13.png");
                    break;
                //von rechts und oben
                case 7:
                    this.tile = new Texture("mapAssets/15.png");
                    break;
                //von rechts und unten
                case 9:
                    this.tile = new Texture("mapAssets/15.png");
                    break;       
            };
            
        //nach oben
        case 2:
            switch(start) {
                //von links und unten
                case 0:
                    this.tile = new Texture("mapAssets/15.png");
                    break;
                //von links
                case 1:
                    this.tile = new Texture("mapAssets/14.png");
                    break; 
                //von recht
                case 3:
                    this.tile = new Texture("mapAssets/14.png");
                    break;
                //von unten
                case 4:
                    this.tile = new Texture("mapAssets/16.png");
                    break;
                //von links und rechts
                case 5:
                    this.tile = new Texture("mapAssets/13.png");
                    break;
                //von rechts und unten
                case 9:
                    this.tile = new Texture("mapAssets/15.png");
                    break;
            
            };
            
        //nach rechts
        case 3:
            switch(start) {
                //von links und unten
                case 0:
                    this.tile = new Texture("mapAssets/15.png");
                    break;
                //von links
                case 1:
                    this.tile = new Texture("mapAssets/16.png");
                    break; 
                //von oben
                case 2:
                    this.tile = new Texture("mapAssets/14.png");
                    break;
                //von unten
                case 4:
                    this.tile = new Texture("mapAssets/14.png");
                    break;
                //von oben und unten
                case 6:
                    this.tile = new Texture("mapAssets/13.png");
                    break;
                //von links und oben
                case 8:
                    this.tile = new Texture("mapAssets/15.png");
                    break;  
            };

        //nach unten
        case 4:
            switch(start) {
                //von links 
                case 1:
                    this.tile = new Texture("mapAssets/14.png");
                    break;
                //von oben
                case 2:
                    this.tile = new Texture("mapAssets/9.png");
                    break; 
                //von rechts
                case 3:
                    this.tile = new Texture("mapAssets/14.png");
                    break;
                //von links und rechts
                case 5:
                    this.tile = new Texture("mapAssets/13.png");
                    break;
                //von rechts und oben
                case 7:
                    this.tile = new Texture("mapAssets/8.png");
                    break;
                //von links und oben
                case 8:
                    this.tile = new Texture("mapAssets/8.png");
                    break;       
          };
    }
    }

    @Override
    public String showAttributes() {
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor
                + ", start: " + this.start + ", end: " + this.end;
        return attributes;
    }

    /**
     * Take from where the Conveyor Belt come from.
     * @return the Start
     */
    public int getStart() {
        return this.start;
    }

    /**
     * Take where the Conveyor Belt goes.
     * @return the End
     */
    public int getEnd() {
        return this.end;
    }
    
    public Texture getTile() {
        return this.tile;
    }
}
