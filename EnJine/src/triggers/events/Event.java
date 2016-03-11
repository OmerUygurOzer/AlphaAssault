package triggers.events;

import ingame.logic.Attributes;
import network.Message;

/**
 * Created by Omer on 3/1/2016.
 */
public abstract class Event implements EventHandler.Attachable, Message {

    protected Attributes attributes;

    protected EventState currentState;

    protected long startTime;
    protected float delay;
    protected float duration;
    protected float frequency;

    protected Event(){
        this.attributes = new Attributes();
        this.currentState = EventState.IDLE;
        this.delay = 0;
    }


    public void setDelay(float delay){
        this.delay = delay;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    @Override
    public void attach(EventHandler eventHandler){
        startTime = System.nanoTime();
        eventHandler.addEvent(this);
    }

    public EventState getCurrentState(){
        return currentState;
    }

    public void setCurrentState(EventState state){ currentState = state;}


    public abstract void process();
    public abstract EventModel getModel();



}
