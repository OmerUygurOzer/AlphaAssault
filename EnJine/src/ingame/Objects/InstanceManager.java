package ingame.objects;

import ingame.logic.AttributeHolder;
import ingame.logic.Attributes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 3/11/2016.
 */
public class InstanceManager implements Serializable{

    private Map<Integer,AttributeHolder> instanceAttributes;

    public InstanceManager(){
        this.instanceAttributes = new HashMap<Integer, AttributeHolder>();
    }

    public void addInstanceData(int id,AttributeHolder attributeHolder){
        this.instanceAttributes.put(id,attributeHolder);
    }




}
