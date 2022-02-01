package htwk.mechawars.ai;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import htwk.mechawars.ConfigReader;

/**
 * Class that manages, imports and saves the AI-Class Objects.
 *
 */
public class AiManager {
    
    LinkedList<AiInterface> aiList = new LinkedList<AiInterface>();
    
    /**
     * Constructor for the AI Manager. 
     * This Method fills the list with Objects of the AI-Classes
     * (that implement the AiInterface)
     * specified in the Startup-Config-File.
     */
    public AiManager() {
        aiList.add(new AiCardGeneration());
        LinkedList<String> list = ConfigReader.getAiList();
        for (int i = 0; i < ConfigReader.getAiList().size(); i++) {
            Class<?> c;
            try {
                c = Class.forName("htwk.mechawars.ai." + list.get(i));
                Constructor<?> cons = c.getConstructor();
                AiInterface object = (AiInterface) cons.newInstance();
                aiList.add(object);
            } catch (ClassCastException | ClassNotFoundException | 
                    NoSuchMethodException | SecurityException | 
                    InstantiationException | IllegalAccessException 
                    | IllegalArgumentException |
                    InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
        }

    }

    /**getter for an Ai specified by the ai Number.
     * 
     * @param aiNumber indexNumber of the chosen AI from the Robot
     * 
     * @return Object of the chosen AI-Class
     */
    public AiInterface getAi(int aiNumber) { 
        if (aiNumber < aiList.size()) {
            return aiList.get(aiNumber );
        }
        return aiList.get(0);     
    }
    
}
