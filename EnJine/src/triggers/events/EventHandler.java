package triggers.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 3/1/2016.
 */
public class EventHandler {
    private EventFilter eventFilter;

    private List<Event> eventsAll;
    private List<Event>  eventsFinished;

    public EventHandler(){
        this.eventFilter = new EventFilter(this);

        eventsAll = new ArrayList<Event>();
        eventsFinished = new ArrayList<Event>();
    }

    public void addEvent(Event event){
        event.attach(this);
    }

    public void addEvents(List<Event> events){
        for(Event event: events){
            event.attach(this);
        }
    }


    public void processEvents(){
        for(Event event: eventsAll){
            eventFilter.filter(event);

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
}
