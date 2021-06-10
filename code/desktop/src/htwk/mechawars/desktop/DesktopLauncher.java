package htwk.mechawars.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import htwk.mechawars.MechaWars;

/**
* Class containing the runner for the desktop frontend.
*/
public class DesktopLauncher {
    /**
    * Main class, running the desktop frontend.
    */
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(800, 480);
        new Lwjgl3Application(new MechaWars(), config);
    }
}
