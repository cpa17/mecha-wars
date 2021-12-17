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

    private static int playerNumber;

    public static void setSkip(boolean skip) {
        isSkip = skip;
    }


    /**
     * Method & Attribute for setup the Map when executing the Program.
     */
    private static String map;

    public static void setMap(String fileName) {
        map = fileName;
    }

    public static String getMap() {
        return map;
    }

    /**
     * Method & Attribute for setup the number of players when executing the Program.
     */
    //private static int playerNumber;

    public static void setPlayerNumber(int playerNr) {
        playerNumber = playerNr;
    }

    /**
     * Method to start either from the main menu or the game screen.
     */
    @Override
    public void create() {
        ConfigReader.writePlayerNumber(playerNumber);

        try {
            ConfigReader.readConfigs();
        } catch (IOException e) {
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
