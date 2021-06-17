package htwk.mechawars;

import com.badlogic.gdx.Game;

/**
* Main game class.
*/
public class MechaWars extends Game {

	public static boolean isSkip = false; //Variable zum Skippen
	
	/**
	 * Method to start either from the main menu or the gamescreen
	 */
    @Override
    public void create() {
    	if(isSkip==false) {
            this.setScreen(new MainMenu(this));
        }
        else {
            this.setScreen(new GameScreen());
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
