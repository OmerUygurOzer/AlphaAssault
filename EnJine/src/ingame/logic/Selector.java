package ingame.logic;

import ingame.World;
import ingame.objects.Entity;

import java.util.List;

/**
 * Created by Omer on 3/11/2016.
 */
public interface Selector {
    List<Entity> select(World world, float x, float y);
}
