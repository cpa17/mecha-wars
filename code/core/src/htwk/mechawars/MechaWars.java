package htwk.mechawars;

import java.io.IOException;

import com.badlogic.gdx.Game;

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

    /**
     * Method to start either from the main menu or the game screen.
     */
    @Override
    public void create() {
    	
        if (isSkip == true) {
            this.setScreen(new GameScreen());
        } else {
            try {
				this.setScreen(new MainMenu(this));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
