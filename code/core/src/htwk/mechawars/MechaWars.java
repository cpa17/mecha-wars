package htwk.mechawars;

import java.io.IOException;

import com.badlogic.gdx.Game;
import htwk.mechawars.game.GameScreen;

/**
 * Main game class.
 */
public class MechaWars extends Game {

    /**
     * Method & Attribute for skipping the MainMenu when executing the Program.
     */
    private static boolean isSkip = false;

    public static void setSkip(boolean skip) {
        isSkip = skip;
    }

    private static String map;

    public static void setMap(String fileName) {
        map = fileName;
    }

    public static String getMap() {
        return map;
    }

    /**
     * Method to start either from the main menu or the game screen.
     */
    @Override
    public void create() {
        try {
            ConfigReader.readConfigs();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (isSkip) {
            this.setScreen(new GameScreen(this, map));
        } else {
            this.setScreen(new MainMenu(this));
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        
    }
}
