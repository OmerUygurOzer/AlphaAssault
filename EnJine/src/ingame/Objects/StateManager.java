package ingame.objects;

import exceptions.GameEngineException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Omer on 3/11/2016.
 */
public class StateManager implements Serializable{

    private Map<Integer,ObjectState> states;
    private Map<Integer,String> idToName;

    public StateManager(){
        this.states   = new HashMap<Integer, ObjectState>();
        this.idToName = new HashMap<Integer, String>();
    }

    public void addInstanceData(Integer id,ObjectState objectState){
        this.states.put(id, objectState);
        this.idToName.put(id,objectState.getText("name"));
    }

    public String findName(Integer id){
        idCheck(id);
        return idToName.get(id);
    }

    public void loadState(Integer id,GameObject gameObject){
        idCheck(id);
        gameObject.objectState = states.get(id).copy();
    }

    public Set<Integer> getIDs(){
        return states.keySet();
    }

    private void idCheck(Integer id){
        if(!states.containsKey(id)){
            throw new GameEngineException("Instance id does not exists in the manager");
        }
    }



}
