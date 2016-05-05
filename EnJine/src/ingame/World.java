package ingame;

import ingame.level.Level;
import ingame.logic.AttributeHolder;
import ingame.logic.Attributes;
import ingame.objects.EntityModel;
import ingame.objects.EntityState;
import ingame.objects.GameObject;
import triggers.events.EventHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Omer on 2/9/2016.
 */
public class World {
    private static int baseID = Integer.MIN_VALUE;

    private EventHandler eventHandler;

    private Map<String,Level> levels;
    private Level currentLevel;

    public World(){
        this.eventHandler = new EventHandler();
        this.levels = new HashMap<String, Level>();
    }



    public void addLevel(Level level){
        this.levels.put(level.name,level);
    }

    public void switchToLevel(String name){
        currentLevel.dispose();
        currentLevel = levels.get(name);
        currentLevel.unpack();
    }

    public Level getCurrentLevel(){return currentLevel;}

    public int getNewID(){return baseID++;}


    public interface WorldObject{
        void instantiate(World world, EntityModel model, AttributeHolder instanceData);
        boolean isInstantiated();
        World getWorld();
        int getID();
    }

}
