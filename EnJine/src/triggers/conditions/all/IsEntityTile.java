package triggers.conditions.all;

import ingame.objects.Entity;
import triggers.conditions.Condition;

/**
 * Created by Omer on 3/10/2016.
 */
public class IsEntityTile implements Condition {

    private Entity target;

    public IsEntityTile(Entity entity){
        this.target = entity;
    }

    @Override
    public boolean isSatisfied() {
        return target.getAttributes().getBinary("isTile");}
}
