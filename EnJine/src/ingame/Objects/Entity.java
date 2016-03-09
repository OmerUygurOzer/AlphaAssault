package ingame.objects;

import handlers.ResourceManager;
import ingame.World;

/**
 * Created by Omer on 3/1/2016.
 */
public class Entity extends GameObject implements ResourceManager.resourceUser{

    public Entity(World world) {
        super(world);
    }

    public Entity(GameObject gameObject) {
        super(gameObject);
    }


    @Override
    public void register(String key) {

    }

    @Override
    public void unregister(String key) {

    }
}
