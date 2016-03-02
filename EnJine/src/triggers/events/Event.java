package triggers.events;

/**
 * Created by Omer on 3/1/2016.
 */
public abstract class Event {


    private EventState currentState;
    private long startTime;
    private float delay;
    private float duration;
    private float frequency;

    protected Event(){
        this.currentState = EventState.IDLE;
        this.delay = 0;
    }

    public void setDelay(int delay){
        this.delay = delay;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public void attach(EventHandler eventHandler){
        startTime = System.nanoTime();
        eventHandler.addEvent(this);
    }

    public EventState getCurrentState(){
        return currentState;
    }


    public abstract void process();




}
