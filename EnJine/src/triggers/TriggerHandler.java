package triggers;

import triggers.events.EventHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 3/7/2016.
 */


public class TriggerHandler {

    private EventHandler eventHandler;

    private Map<String,List<Trigger>> triggerMap;

    public TriggerHandler(EventHandler eventHandler){
        this.eventHandler = eventHandler;
        this.triggerMap = new HashMap<String, List<Trigger>>();
    }

    public void addTrigger(Trigger trigger){
        if(!triggerMap.containsKey(trigger.cause)){
            List<Trigger> list = new ArrayList<Trigger>();
            list.add(trigger);
            triggerMap.put(trigger.cause,list);
            return;
        }
        triggerMap.get(trigger.cause).add(trigger);
    }

    public void filter(String tag){
        for(Trigger trigger: triggerMap.get(tag)){
            trigger.fire(eventHandler);
        }
    }


}
