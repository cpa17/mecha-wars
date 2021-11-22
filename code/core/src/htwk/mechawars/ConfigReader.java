package htwk.mechawars;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
	
	private static int gameModeconfig = 0;
	private static int aiMode = 0;
	private static int playerNumber = 1;
	private static boolean[] AImodes= {false,false,false,false,false,false,false,false};
	private static Point[] Playerpoints = {
			new Point (1,1),
			new Point (2,2),
			new Point (3,3),
			new Point (4,4),
			new Point (5,5),
			new Point (6,6),
			new Point (7,7),
			new Point (8,8)
	};
	

	public static void readConfigs() throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("..//configs//Startupconfig.txt"));
	try {
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
	    	readConfig(line);
	    	line = br.readLine();
	    }

	} finally {
	    br.close();
	}

}

	private static void readConfig(String line) {
		// TODO Auto-generated method stub

		
		if(line.contains("playerNumber"))
		{
			playerNumber = Integer.parseInt(line.replaceAll("\\D+",""));
		}

		
		if(line.contains("playerposition"))
		{
			
			String[] s = line.split("-");
				
				line = line.replaceAll("\\p{P}","");
				line = line.replaceAll("[a-z]","");


		
			for(int i = 0 ; i < s.length; i++)
			{
				s[i] = s[i].replaceAll("\\p{P}","");
				s[i] = s[i].replaceAll("[a-z]","");
				s[i] = s[i].replaceAll(" ","");
				System.out.println(s[i]);	
			}
			Playerpoints[Integer.parseInt(s[0])] = new Point(Integer.parseInt(s[1]),Integer.parseInt(s[2]));
			boolean AI = false;
			if(Integer.parseInt(s[3])==1)
					{
				AI = true;
					}
			AImodes[Integer.parseInt(s[0])] = AI;
			
		}
		
	}
	
	public static boolean[] getAImodes() {
		return AImodes;
	}

	public static Point[] getPlayerpoints() {
		return Playerpoints;
	}

	public static void setPlayerpoints(Point[] playerpoints) {
		Playerpoints = playerpoints;
	}

	public int getGameModeconfig() {
		return gameModeconfig;
	}

	public void setGameModeconfig(int gameModeconfig) {
		this.gameModeconfig = gameModeconfig;
	}

	public int getAiMode() {
		return aiMode;
	}

	public void setAiMode(int aiMode) {
		this.aiMode = aiMode;
	}

	public static int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}


	}
