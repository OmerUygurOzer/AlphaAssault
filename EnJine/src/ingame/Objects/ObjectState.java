package ingame.objects;

import ingame.logic.Attributes;

/**
 * Created by Omer on 3/9/2016.
 */
public class ObjectState extends Attributes {


    @Override
    public ObjectState copy() {
        ObjectState objectState = new ObjectState();
        objectState.insert(this);
        return objectState;
    }
}
