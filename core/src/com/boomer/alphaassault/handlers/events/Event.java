package com.boomer.alphaassault.handlers.events;

import com.boomer.alphaassault.gameworld.GameWorld;

import java.io.Serializable;

/**
 * Created by Omer on 12/24/2015.
 */
public abstract class Event {

    protected  GameWorld world;

    protected Event(GameWorld _world){
        world = _world;
    }

    public abstract void process();



}
