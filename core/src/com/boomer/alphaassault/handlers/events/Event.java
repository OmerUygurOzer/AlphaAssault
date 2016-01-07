package com.boomer.alphaassault.handlers.events;

import com.boomer.alphaassault.exceptions.GameEngineException;
import com.boomer.alphaassault.gameworld.GameWorld;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 12/24/2015.
 */
public abstract class Event {

    protected  GameWorld world;
    protected String message;

    private boolean doesHaveMessage;

    protected Event(GameWorld _world){
        world = _world;
        doesHaveMessage = false;
    }

    public abstract void process();


    public void setMessage(String _message){message =  _message; doesHaveMessage = true;}
    public String getMessage(){
        if(!doesHaveMessage){throw new GameEngineException("Event has no message set");}
        return message;}

    public byte[] serialize(){
        if(!doesHaveMessage){throw new GameEngineException("Event has no message set");}
        return message.getBytes();}

}
