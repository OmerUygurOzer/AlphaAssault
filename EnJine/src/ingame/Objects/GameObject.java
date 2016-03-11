package ingame.objects;


import ingame.World;
import ingame.logic.Attributes;
import ingame.logic.Component;
import ingame.logic.ComponentParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 2/5/2016.
 */
public abstract class GameObject {

    private int instanceID;

    protected Attributes attributes;

    public GameObject(){this.attributes = new Attributes();}

    public void setAttributes(Attributes attributes){
        this.attributes = attributes;
    }

    public Attributes getAttributes(){
        return attributes;
    }

}
