package ingame.objects;

import ingame.logic.Attributes;
import resources.UsedResources;

import java.io.Serializable;


/**
 * Created by Omer on 3/10/2016.
 */
public class ObjectModel implements Serializable{

    public UsedResources usedResources;
    public Attributes modelAttributes;

    public ObjectModel(){
        this.usedResources = new UsedResources();
        this.modelAttributes  = new Attributes();
    }

    public ObjectModel getCopy(){
        ObjectModel copy = new ObjectModel();
        copy.usedResources   = usedResources.getCopy();
        copy.modelAttributes = modelAttributes.copy();
        return copy;
    }

}
