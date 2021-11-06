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
            this.setScreen(new MainMenu(this));
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        /*
        if (JOptionPane.showConfirmDialog(null, 
                "Programm wird beendet.", "Schließen?", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION){
            // tu was - nicht beenden
            dispose();
        }//Ende Aktion des if-Teils
        // falls ja ausgewaehlt -> beenden
        */
    }
}
