package com.boomer.alphaassault.gameworld.events;

import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.handlers.events.Event;

/**
 * Created by Omer on 12/26/2015.
 */
public class EntityCreated extends Event {

    public static final char SIZE = 's';
    public static final char POS_X = 'x';
    public static final char POS_Y = 'y';
    public static final char DEPTH = 'd';

    public EntityCreated(GameWorld _world) {
        super(_world);
    }

    public void setClassName(String _className){
        className = _className;
    }

    public void setAttribute(char attribute, double _value){
        attributes.put(attribute,_value)  ;
    }

    public void createMessage(){
        message += className + "s:" + attributes.get(SIZE) + " x:" + attributes.get(POS_X) + " y:" +attributes.get(POS_Y) + " d:" + attributes.get(DEPTH);
    }

    @Override
    public void process() {
        System.out.println(message);
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }
}
