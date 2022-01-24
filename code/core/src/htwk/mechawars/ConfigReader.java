package htwk.mechawars;

import java.awt.Point;
import java.io.IOException;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Class that reads the config File and saves the configurations in fields.
 * 
 */
public class ConfigReader {
    private static int playerNumber = 1;
    private static int[] aiModes = {0, 0, 0, 0, 0, 0, 0, 0};
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
    private static LinkedList<String> aiList = new LinkedList<>();
    
    /**
     * Function that reads the Game Configurations from Startupconfig.txt.
     */
    public static void readConfigs() throws IOException {
        FileHandle file = Gdx.files.internal("configs/Startupconfig.txt");
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
        aiList = new LinkedList<>();
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
            int ai = Integer.parseInt(s[3]);
            aiModes[Integer.parseInt(s[0])] = ai;   
        
        }
        if (line.contains("AiImport: ")) {
            line = line.replace("AiImport: ", "");
            System.out.println(line + "  eingelesen!");
            aiList.add(line);
        }
        
    }
    
    
    /**
     * Function that outputs the Array of indiviudal AI modes.
     * 
     * @return Array of the ai mode of each individual Player.
     */
    public static int[] getAimodes() {
        int[] aiModes = ConfigReader.aiModes;
        return aiModes;
    }

    /**
     * Function that outputs the Array of player startingpositions modes.
     * 
     * @return Array of the starting position of each individual Player.
     */
    public static Point[] getPlayerStartingPositions() {
        Point[] playerStartingPositions = ConfigReader.playerStartingPositions;
        return playerStartingPositions;
    }


    /**
     * Function that outputs the number of players.
     * 
     * @return the number of players.
     */
    public static int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Function that setup the Startupconfig.
     * @param playerNumber the number of players.
     */
    public static void writePlayerNumber(int playerNumber) {
        String player = String.valueOf(playerNumber);

        FileHandle file = Gdx.files.local("configs/Startupconfig.txt");
        file.writeString("playerNumber: " + player, false);

        /*for (int current = 0; current < playerNumber; current++) {
            file.writeString("\nplayerposition: "
                    + current + "-" + 3 + "-" + (3 + current) + "-" + aiModes[current], true);
        }
        file.writeString("\nAiImport: aic2", true);*/

    }

    public static LinkedList<String> getAiList() {
        return aiList;
    }

    public static void setAiList(LinkedList<String> aiList) {
        ConfigReader.aiList = aiList;
    }
}

