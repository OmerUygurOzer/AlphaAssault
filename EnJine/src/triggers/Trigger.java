package triggers;

import triggers.events.Event;
import triggers.events.EventHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 3/7/2016.
 */
public class Trigger {

    public String cause;
    private List<Event> endResult;

    public Trigger(String tag){
        this.cause = tag;
        endResult = new ArrayList<Event>();
    }


    public void addResult(Event event){
        endResult.add(event);
    }

    public void addResults(List<Event> events){
        endResult.addAll(events);
    }

    public void fire(EventHandler eventHandler){
        for(Event event: endResult){
            event.attach(eventHandler);
        }
    };
}
