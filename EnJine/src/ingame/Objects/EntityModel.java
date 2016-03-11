package ingame.objects;

import ingame.logic.Attributes;
import resources.UsedResources;

import java.io.Serializable;


/**
 * Created by Omer on 3/10/2016.
 */
public class EntityModel implements Serializable{

    public UsedResources usedResources;
    public Attributes modelAttributes;

    public EntityModel(String tag){
        this.usedResources = new UsedResources();
        this.modelAttributes  = new Attributes();
        this.modelAttributes.addAttribute("modelTag",tag);
    }

    public String getTag() {
        return modelAttributes.getText("modelTag");
    }
}
