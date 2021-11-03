package htwk.mechawars;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
	
	private static int gameModeconfig = 0;
	private static int aiMode = 0;
	private static int playerNumber = 1;

	public static int[] readConfigs() throws IOException {
		System.out.println("sheesh");
	BufferedReader br = new BufferedReader(new FileReader("C://Users//Nutzer//git//repository//mecha-wars//code//core//configs//Startupconfig.txt"));
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
	return new int[] {gameModeconfig ,aiMode ,playerNumber};
}

	private static void readConfig(String line) {
		// TODO Auto-generated method stub
		if(line.contains("gameMode"))
		{
			if(line.contains("singleplayer"))
			{
				gameModeconfig = 0;
			}
			else if(line.contains("multiplayer"))
			{
				gameModeconfig = 1;
			}
			else 
			{
				gameModeconfig = 0;
			}
		}
		
		else if(line.contains("AImode"))
		{
			if(line.contains("idle"))
			{
				aiMode = 0;
			}
			else if(line.contains("active"))
			{
				aiMode = 1;
			}
			else 
			{
				aiMode = 0;
			}
		}
		
		else if(line.contains("playerNumber"))
		{
			playerNumber = Integer.parseInt(line.replaceAll("\\D+",""));
		}
		
		
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

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	}
