package htwk.mechawars;

import com.badlogic.gdx.Game;

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

    public static void setPlayerNumber(int playerNr) {
        playerNumber = playerNr;
    }

    public static int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Method to start either from the main menu or the game screen.
     */
    @Override
    public void create() {
       
        if (isSkip == true) {
            this.setScreen(new GameScreen());
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
