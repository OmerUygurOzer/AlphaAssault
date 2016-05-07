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
            if(resourceData.atlas.findRegion(region)!=null) {
                gameObject.loadedResources.regions.put(region, resourceData.atlas.findRegion(region));
            }else{
                System.out.println("TextureRegion " + region + " can not be loaded.");
            }
        }

        for(String music:gameObject.objectModel.usedResources.musics){
            if(resourceData.musicResources.containsKey(music)) {
                gameObject.loadedResources.musics.put(music, resourceData.musicResources.get(music));
            }else{
                System.out.println("Music " + music + " can not be loaded.");
            }
        }

        for(String sound:gameObject.objectModel.usedResources.sounds){
            if(resourceData.soundResources.containsKey(sound)) {
                gameObject.loadedResources.sounds.put(sound, resourceData.soundResources.get(sound));
            }else{
                System.out.println("Sound " + sound + " can not be loaded.");
            }
        }

    }


}
