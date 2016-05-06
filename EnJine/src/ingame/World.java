package ingame;

import exceptions.GameEngineException;
import ingame.level.Level;
import triggers.events.EventHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Omer on 2/9/2016.
 */
public class World {
    private static int baseID = Integer.MIN_VALUE;

    private EventHandler eventHandler;

    private Map<Integer,WorldObject> objects;
    private Map<String,Level> levels;
    private Level currentLevel;


    public World(){
        this.eventHandler = new EventHandler();
        this.levels       = new HashMap<String, Level>();
        this.objects      = new HashMap<Integer, WorldObject>();
    }



    public void addLevel(Level level){
        this.levels.put(level.name,level);
    }

    public void switchToLevel(String name){
        if(!levels.containsKey(name)){throw new GameEngineException("Level does not exist");}
        currentLevel.dispose();
        currentLevel = levels.get(name);
        currentLevel.unpack();
    }

    private void loadLevel(String name){

    }

    public Level getCurrentLevel(){return currentLevel;}

    public int getNewID(){return baseID++;}


    public interface WorldObject{
        void newInstance(World world);
        void loadInstance(World world,int id);
        boolean isInstantiated();
        World getWorld();
        int getID();
    }

}
