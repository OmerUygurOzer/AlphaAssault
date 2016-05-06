package ingame.objects;

import exceptions.GameEngineException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 3/10/2016.
 */
public class ModelManager implements Serializable{
    private Map<String,ObjectModel> modelMap;

    public ModelManager(){
        this.modelMap = new HashMap<String, ObjectModel>();
    }

    public void addModel(String tag, ObjectModel objectModel){
        tagCheck(tag);
        modelMap.put(tag, objectModel);
    }

    public void removeModel(String tag,ObjectModel objectModel){
        checkForModel(tag);
        modelMap.remove(tag);
    }

    public void loadModel(String tag,GameObject gameObject){
        checkForModel(tag);
        gameObject.objectModel = modelMap.get(tag).getCopy();
    }

    private void checkForModel(String tag){
        if(!modelMap.containsKey(tag)){
            throw new GameEngineException("Model does not exists");
        }
    }

    private void tagCheck(String tag){
        if(modelMap.containsKey(tag)){
            throw new GameEngineException("Two models can not have the same tag.");
        }
    }

}
