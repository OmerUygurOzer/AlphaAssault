package ingame;

import ingame.level.Level;
import ingame.objects.EntityModel;
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



    public int getNewID(){return baseID++;}


    public interface WorldObject{
        void instantiate(World world, EntityModel model);
        boolean isInstantiated();
        World getWorld();
        int getID();
    }

}
