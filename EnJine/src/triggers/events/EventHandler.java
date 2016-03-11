package triggers.events;

import triggers.Trigger;
import triggers.TriggerHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 3/1/2016.
 */
public class EventHandler {

    private TriggerHandler triggerHandler;

    private List<Event> eventsAll;
    private List<Event>  eventsFinished;


    public EventHandler(){
        this.eventsAll = new ArrayList<Event>();
        this.eventsFinished = new ArrayList<Event>();
    }

    public void setTriggerHandler(TriggerHandler triggerHandler){
        this.triggerHandler = new TriggerHandler();
        this.triggerHandler.setEventHandler(this);
    }

    public void addEvent(Event event){
        eventsAll.add(event);
    }

    public void addEvents(List<Event> events){
        eventsAll.addAll(events);
    }

    public void addTrigger(Trigger trigger){
        triggerHandler.addTrigger(trigger);
    }

    public void processEvents(){
        for(Event event: eventsAll){
            triggerHandler.filter(event.attributes.getText("tag"));

            if(event.getCurrentState() == EventState.ACTIVE){
                event.process();
            }

            if(event.getCurrentState() == EventState.FINISHED){
                eventsFinished.add(event);
            }
        }

        eventsAll.removeAll(eventsFinished);
        eventsFinished.clear();
    }

    public void clear(){
        eventsAll.clear();
        eventsFinished.clear();
    }

    public interface Attachable{
        void attach(EventHandler eventHandler);
    }
}
