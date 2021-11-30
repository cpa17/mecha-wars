package htwk.mechawars;

import java.awt.Point;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Class that reads the config File and saves the configurations in fields.
 * 
 */
public class ConfigReader {
    private static int playerNumber = 1;
    private static boolean[] aiModes = {false, false, false, false, false, false, false, false};
    private static Point[] playerStartingPositions = {
            new Point(1, 1), 
            new Point(2, 2), 
            new Point(3, 3), 
            new Point(4, 4), 
            new Point(5, 5), 
            new Point(6, 6), 
            new Point(7, 7), 
            new Point(8, 8)
    };
    
    /**
     * Function that reads the Game Configurations from Startupconfig.txt.
     */
    public static void readConfigs() throws IOException {
        FileHandle file = Gdx.files.internal("..//assets//configs//Startupconfig.txt");
        String fileString = file.readString();
        String[] lines = fileString.split("\\r?\\n");
        for (String line : lines) {
            readConfig(line);
        }
    }
    
    /**
     * Function that interprets a line from the config file.
     *
     * @param line from Startupconfig.txt.
     */
    private static void readConfig(String line) {
        // TODO Auto-generated method stub

        if (line.contains("playerNumber")) {
            playerNumber = Integer.parseInt(line.replaceAll("\\D+", ""));
        }

        if (line.contains("playerposition")) {
            String[] s = line.split("-");       
            for (int i = 0; i < s.length; i++) {
                s[i] = s[i].replaceAll("\\p{P}", "");
                s[i] = s[i].replaceAll("[a-z]", "");
                s[i] = s[i].replaceAll(" ", "");  
            }
            playerStartingPositions[Integer.parseInt(s[0])] =
                    new Point(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            boolean ai = false;
            if (Integer.parseInt(s[3]) == 1) {
                ai = true;
            }
            aiModes[Integer.parseInt(s[0])] = ai;   
        }      
    }
    
    
    /**
     * Function that outputs the Array of indiviudal AI modes.
     * 
     * @return Array of the ai mode of each individual Player.
     */
    public static boolean[] getAimodes() {
        boolean[] aiModesCopy = aiModes;
        return aiModesCopy;
    }

    /**
     * Function that outputs the Array of player startingpositions modes.
     * 
     * @return Array of the starting position of each individual Player.
     */
    public static Point[] getPlayerStartingPositions() {
        Point[] playerPointsCopy = playerStartingPositions;
        return playerPointsCopy;
    }


    /**
     * Function that outputs the number of players.
     * 
     * @return the number of players.
     */
    public static int getPlayerNumber() {
        int playerNumberCopy = playerNumber;
        return playerNumberCopy;
    }

}
