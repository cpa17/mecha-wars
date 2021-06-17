package htwk.mechawars.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import htwk.mechawars.MechaWars;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


/**
* Class containing the runner for the desktop frontend.
*/
@Command(name = "Mecha Wars",
version = "Mecha Wars 1.0",
description =  "@||fg(red) Stores the current contents of the index in a new commit " + "along with a log message from the user describing the changes.|@")
public class DesktopLauncher implements Runnable{
	
	@Option(names = { "-s", "--skip"}, description="Starts the Game, without showing the MainMenu at first." )
	boolean skip;
	
	/**
	* Main class, for the new CommandLine
	*/
    public static void main(String[] arg) {
		new CommandLine(new DesktopLauncher()).execute(arg);
    }
    
    @Option(names = {"-h", "--help"}, usageHelp = true,
    description = "Print usage help and exit.")
    boolean usageHelpRequested;
    
    /**
	* Method, running the desktop frontend.
	*/
    @Override 
	public void run() {
	if(skip == true) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1280, 720);
		MechaWars.isSkip = true;
		new Lwjgl3Application(new MechaWars(), config);
	}
	else {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1280, 720);
		new Lwjgl3Application(new MechaWars(), config);
	}
}
}

//JAR: .\gradlew.bat desktop:dist in der Konsole, im Code Ordner
//die JAR-File liegt dann unter -> code -> desktop -> build -> libs
