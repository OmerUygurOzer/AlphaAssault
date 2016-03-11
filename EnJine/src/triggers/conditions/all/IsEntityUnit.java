package triggers.conditions.all;

import ingame.objects.Entity;
import triggers.conditions.Condition;

/**
 * Created by Omer on 3/10/2016.
 */
public class IsEntityUnit implements Condition {

    private Entity target;

    public IsEntityUnit(Entity entity){
        this.target = entity;
    }

    @Override
    public boolean isSatisfied() {
        return target.getAttributes().getBinary("isUnit");}
}
