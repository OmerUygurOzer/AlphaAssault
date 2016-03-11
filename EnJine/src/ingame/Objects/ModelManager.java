package ingame.objects;

import exceptions.GameEngineException;
import resources.UsedResources;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 3/10/2016.
 */
public class ModelManager implements Serializable{
    private Map<String,EntityModel> modelMap;

    public ModelManager(){
        this.modelMap = new HashMap<String, EntityModel>();
    }


    public void createNewModel(String tag){
        tagCheck(tag);
        modelMap.put(tag,new EntityModel(tag));
    }

    public void addAttributeToModel(String tag,String attribKey,double numeric){
        modelMap.get(tag).modelAttributes.addAttribute(attribKey,numeric);
    }

    public void addAttributeToModel(String tag,String attribKey,boolean binary){
        modelMap.get(tag).modelAttributes.addAttribute(attribKey,binary);
    }

    public void addAttributeToModel(String tag,String attribKey,String text){
        modelMap.get(tag).modelAttributes.addAttribute(attribKey,text);
    }

    public void addResourceToModel(String tag, UsedResources.Type type, String resource){
        switch (type){
            case REGION:
                modelMap.get(tag).usedResources.atlasRegions.add(resource);
                break;
            case SOUND:
                modelMap.get(tag).usedResources.sounds.add(resource);
                break;
            case MUSIC:
                modelMap.get(tag).usedResources.music.add(resource);
                break;
        }
    }

    public void addModel(EntityModel entityModel){
        modelMap.put(entityModel.getTag(),entityModel);
    }

    public EntityModel getModel(String tag){return modelMap.get(tag);}

    private void tagCheck(String tag){
        if(modelMap.containsKey(tag)){
            throw new GameEngineException("Two models can not have the same tag.");
        }
    }

}
