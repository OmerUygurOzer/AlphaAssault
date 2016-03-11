package triggers;

import triggers.events.Event;
import triggers.events.EventHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 3/7/2016.
 */
public class Trigger implements Serializable {

    private Trigger.State currentState;

    public String cause;
    private List<Event> endResult;

    public Trigger(){
        this.currentState = State.ACTIVE;
        this.endResult = new ArrayList<Event>();
    }

    public void setCause(String tag){
        this.cause = tag;
    }


    public void addResult(Event event){
        endResult.add(event);
    }

    public void addResults(List<Event> events){
        endResult.addAll(events);
    }

    public void fire(EventHandler eventHandler){
        if(currentState == State.ACTIVE) {
            for (Event event : endResult) {
                event.attach(eventHandler);
            }
        }
    }

    public State getCurrentState(){
        return currentState;
    }

    public enum State{
        ACTIVE,
        IDLE,
        DONE;
    }
}
