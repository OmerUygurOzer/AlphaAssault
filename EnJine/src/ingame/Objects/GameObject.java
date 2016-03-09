package ingame.objects;


import ingame.World;
import ingame.logic.Attributes;
import ingame.logic.Component;
import ingame.logic.ComponentParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 2/5/2016.
 */
public abstract class GameObject {

    private int instanceID;

    private World world;

    private Attributes attributes;
    private Map<String, Component> components;



    public GameObject(World world){
        this.world = world;
        this.instanceID = world.getID();
        this.attributes = new Attributes();
        this.components = new HashMap<String, Component>();
    }

    public GameObject(GameObject gameObject){
        this.attributes = gameObject.attributes.copy();
        this.components = ComponentParser.parseComponents(gameObject.components.keySet());
        this.world = gameObject.world;
        this.instanceID = world.getID();

    }

    public void setComponents(Map<String,Component> components){
        this.components = components;
    }

    public Map<String,Component> getComponents(){
        return components;
    }

    public void setAttributes(Attributes attributes){
        this.attributes = attributes;
    }

    public Attributes getAttributes(){
        return attributes;
    }

    public World getWorld(){return world;}

    public int getInstanceID(){return instanceID;}
}
