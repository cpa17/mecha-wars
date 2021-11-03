package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Start Field.
 */
public class StartField extends Field {

    // Attribut was angibt welche Nummer das Startfeld hat
    // 1 = Startfeld Nr. 1, 2 = Startfeld Nr.2, ...
    private int number;
    private Texture tile;

    /**
     * Constructor of a Start Field.
     */
    public StartField(int xcoor, int ycoor, int number) {
        super(xcoor, ycoor);
        this.number = number; 
        
        this.tile = new Texture(Gdx.files.internal("mapAssets/" + "startfield/" + "StartField0" + String.valueOf(number) + ".png"));
        
        /*
        switch(number){
        case 1:
            this.tile = new Texture("mapAssets/27.png");
            break;
        case 2:
            this.tile = new Texture("mapAssets/28.png");
            break;
        case 3:
            this.tile = new Texture("mapAssets/29.png");
            break;
        case 4:
            this.tile = new Texture("mapAssets/30.png");
            break; 
        case 5:
            this.tile = new Texture("mapAssets/31.png"); //6
            break; 
        case 6:
            this.tile = new Texture("mapAssets/32.png");
            break;
        case 7:
            this.tile = new Texture("mapAssets/33.png");
            break; 
        case 8:
            this.tile = new Texture("mapAssets/34.png"); //6
            break; 
        }
        */
    }

    @Override
    public String showAttributes(){
        String attributes = "xcoor: " + this.xcoor + ", ycoor: " + this.ycoor + ", number: " + this.number;
        return attributes;
    }

    /**
     * Take the Number of the Start Field.
     * @return the Number
     */
    public int getNumber(){
        return this.number;
    }
    
    public Texture getTile(){
        return this.tile;
    }
}
