package ingame.objects;

import ingame.World;
import resources.ResourceUser;

/**
 * Created by Omer on 3/1/2016.
 */
public abstract class Entity extends GameObject implements ResourceUser, World.WorldObject {

    protected Entity() {
        super();
    }


}
