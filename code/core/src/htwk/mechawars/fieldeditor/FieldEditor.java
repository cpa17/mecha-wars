package htwk.mechawars.fieldeditor;

import com.badlogic.gdx.Game;

public class FieldEditor extends Game {

    private static String map;

    public static void setMap(String fileName) {
        map = fileName;
    }

    public static String getMap() {
        return map;
    }

    @Override
    public void create() {
        
        this.setScreen(new FieldeditGUI(this, map));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        
    }
}
