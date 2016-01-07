package com.boomer.alphaassault.handlers.events;

import com.boomer.alphaassault.gameworld.GameWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 12/24/2015.
 */
public class EventHandler {

    private List<Event> events;
    private List<Event> returnBuffer;
    private List<Event> flushBuffer;

    private final Object accessLock;

    private int eventIndex;

    public EventHandler(){
        events = new ArrayList<Event>();
        returnBuffer = new ArrayList<Event>();
        flushBuffer = new ArrayList<Event>();

        accessLock = new Object();

        eventIndex = 0;
    }

    public List<Event> getEvents(){
        synchronized (accessLock) {
            returnBuffer.clear();
            returnBuffer.addAll(events);
        }
        return returnBuffer;
    }

    public void raiseEvent(Event _event){
        synchronized (accessLock){
            events.add(_event);
        }
    }


    public void processEvents(GameWorld gameWorld){
        synchronized (accessLock){
            while(eventIndex != events.size()){
                events.get(eventIndex).process(gameWorld);
                eventIndex++;
            }

        }
    }

    public List<Event> flushEvents(){
        synchronized (accessLock){
            flushBuffer.clear();
              for(int i = eventIndex ; i<events.size();i++){
                  flushBuffer.add(events.get(eventIndex));
              }
        }
        return flushBuffer;
    }

}
