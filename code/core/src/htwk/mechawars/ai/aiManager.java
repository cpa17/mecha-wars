package htwk.mechawars.ai;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import htwk.mechawars.ConfigReader;

public class aiManager {
    LinkedList<AiInterface> aiList = new LinkedList<AiInterface>();
    public aiManager()
    {
        aiList.add(new AiCardGeneration());
        LinkedList<String> list = ConfigReader.getAiList();
        for(int i = 0; i < ConfigReader.getAiList().size(); i++)
        {
        Class<?> c;
        try {
            c = Class.forName("htwk.mechawars.ai."+list.get(i));
            Constructor<?> cons = c.getConstructor();
            AiInterface object = (AiInterface)cons.newInstance();
            aiList.add(object);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}

    public AiInterface getAI(int aiNumber)
    {
        return aiList.get(aiNumber-1);
     
    }
}
