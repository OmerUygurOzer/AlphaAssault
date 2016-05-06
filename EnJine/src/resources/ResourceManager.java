package resources;


import ingame.objects.GameObject;

import java.io.Serializable;

/**
 * Created by Omer on 3/9/2016.
 */
public class ResourceManager implements Serializable{

    private ResourceData resourceData;

    public ResourceManager(){  }

    public void setResourceData(ResourceData resourceData){
        this.resourceData = resourceData;
    }


    public void loadResources(GameObject gameObject){
        for(String region:gameObject.objectModel.usedResources.atlasRegions){
            gameObject.loadedResources.regions.put(region,resourceData.atlas.findRegion(region));
        }

        for(String music:gameObject.objectModel.usedResources.musics){
            gameObject.loadedResources.musics.put(music,resourceData.musicResources.get(music));
        }

        for(String sound:gameObject.objectModel.usedResources.sounds){
            gameObject.loadedResources.sounds.put(sound,resourceData.soundResources.get(sound));
        }

    }


}
