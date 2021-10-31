package htwk.mechawars.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Class of a Standard Field.
 */
public class StandardField extends Field {

    private Texture tile;

    /**
     * Constructor of a Standard Field.
     */
    public StandardField(int xcoor, int ycoor) {
        super(xcoor, ycoor);
        this.tile = new Texture("mapAssets/0.png"); // = new Texture(Gdx.files.internal("mapAssets/" + i + ".png"));
    }
    
    public Texture getTile(){
        return this.tile;
    }
}
