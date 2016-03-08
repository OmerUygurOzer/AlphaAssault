package ingame.objects;


import ingame.logic.Attributes;
import ingame.logic.Component;
import ingame.logic.ComponentParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 2/5/2016.
 */
public abstract class GameObject {

    private Attributes attributes;
    private Map<String, Component> components;

    protected GameObject(){
        this.attributes = new Attributes();
        this.components = new HashMap<String, Component>();
    }

    public GameObject(GameObject gameObject){
        attributes = gameObject.getAttributes().copy();
        components = ComponentParser.parseComponents(gameObject.getComponents().keySet());
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

}
