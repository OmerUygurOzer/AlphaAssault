package triggers;

import triggers.events.EventHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 3/7/2016.
 */


public class TriggerHandler implements Serializable{

    private transient EventHandler eventHandler;

    private Map<String,List<Trigger>> triggerMap;
    private List<Trigger> finishedTriggers;

    public TriggerHandler(){
        this.triggerMap = new HashMap<String, List<Trigger>>();
        this.finishedTriggers = new ArrayList<Trigger>();
    }

    public void setEventHandler(EventHandler eventHandler){
        this.eventHandler = eventHandler;
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
            if(trigger.getCurrentState()== Trigger.State.DONE){
                finishedTriggers.add(trigger);
            }
        }
        triggerMap.get(tag).removeAll(finishedTriggers);
        finishedTriggers.clear();


    }


}
