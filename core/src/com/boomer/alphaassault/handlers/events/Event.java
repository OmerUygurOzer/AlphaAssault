package com.boomer.alphaassault.handlers.events;

import com.boomer.alphaassault.gameworld.GameWorld;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 12/24/2015.
 */
public abstract class Event {

    protected  GameWorld world;

    protected Map<Character,Double> attributes;

    protected String className;

    protected String message;


    protected Event(GameWorld _world){
        world = _world;
        attributes = new HashMap<Character, Double>();

        message = "";
        className = "";
    }

    public abstract void process();
    public abstract byte[] serialize();

    public String getMessage(){return message;}

}
