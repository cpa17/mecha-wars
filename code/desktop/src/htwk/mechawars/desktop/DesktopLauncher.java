package htwk.mechawars.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import htwk.mechawars.MechaWars;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Class containing the runner for the desktop frontend.
 * Write .\gradlew.bat desktop:dist in your console, 
 * in the writer code in order to provide the jar-file.
 * One can find the jar-file under mecha-wars -> code -> desktop -> build -> libs.
 * Execute the file in your shell with the command 
 * java -jar mw.jar, get all options with the command java -jar mw.jar -h.
 */

@Command(
        version = "Mecha Wars 1.0",
        header = "Mecha Wars",
        mixinStandardHelpOptions = true)
public class DesktopLauncher implements Runnable {

    @Option(names = { "-s", "--skip" }, 
            description = "Starts the Game, without showing the MainMenu at first.")
    boolean skip;

    /**
     * Main class, for the new CommandLine.
     */
    public static void main(String[] args) {
        System.exit(new CommandLine(new DesktopLauncher()).execute(args));
    }

    /**
     * Method, running the desktop frontend.
     */
    @Override 
    public void run() {
        MechaWars.setSkip(skip);
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1280, 720);
        new Lwjgl3Application(new MechaWars(), config);
    }
}



