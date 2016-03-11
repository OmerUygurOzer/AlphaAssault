package ingame;

import ingame.objects.EntityModel;
import triggers.events.EventHandler;


/**
 * Created by Omer on 2/9/2016.
 */
public class World {
    private static int baseID = Integer.MIN_VALUE;

    private EventHandler eventHandler;




    public World(){
        eventHandler = new EventHandler();
    }







    public int getNewID(){return baseID++;}


    public interface WorldObject{
        void instantiate(World world, EntityModel model);
        boolean isInstantiated();
        World getWorld();
        int getID();
    }

}
