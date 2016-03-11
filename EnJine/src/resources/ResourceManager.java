package resources;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 3/9/2016.
 */
public class ResourceManager implements Serializable{

    private ResourceData resourceData;

    private Map<String,UsedResources> usedResourcesMap;

    public ResourceManager(){
            usedResourcesMap = new HashMap<String, UsedResources>();
    }

    public void setResourceData(ResourceData resourceData){
        this.resourceData = resourceData;
    }

    public void addUser(ResourceUser resourceUser){
        String name = resourceUser.getName();
        UsedResources usedResources = resourceUser.getUsedResources();
        usedResourcesMap.put(name,usedResources);
    }


}
