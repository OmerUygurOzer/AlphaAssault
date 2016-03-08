package ingame.logic;

import ingame.objects.Entity;
import triggers.conditions.Condition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 3/7/2016.
 */
public class Group {
    private Map<String,Entity> group;
    private Map<String,Group> subgroups;
    private List<Entity> all;

    public Group(){
        this.group = new HashMap<String, Entity>();
        this.subgroups = new HashMap<String, Group>();
        this.all = new ArrayList<Entity>();
    }

    public void addEntity(String key,Entity entity){
        group.put(key,entity);
        all.add(entity);
    }

    public Entity getEntity(String key){
        if(group.containsKey(key))return group.get(key);
        return null;
    }

    public void removeEntity(String key){
        if (group.containsKey(key)){
            all.remove(group.get(key));
            group.remove(key);
        }
    }

    public void addSubGroup(String key,Group subgroup){
        subgroups.put(key,subgroup);

    }

    public Group getSubGroup(String key){
        if(subgroups.containsKey(key))return subgroups.get(key);
        return null;
    }

    public void generateSubGroup(Condition filter){
       /*Implement
        */
    }

    public void removeSubGroup(String key){
        if (subgroups.containsKey(key)){
            subgroups.remove(key);
        }
    }

    public boolean hasEntity(String key){
        return group.containsKey(key);
    }

    public boolean hasSubGroup(String key){
        return subgroups.containsKey(key);
    }

    public void clear(){
        group.clear();
        subgroups.clear();
        all.clear();

    }
}
