package triggers.events.all;

import com.badlogic.gdx.math.Vector2;
import ingame.objects.Entity;
import triggers.Trigger;
import triggers.events.Event;
import triggers.events.EventModel;
import triggers.events.EventState;

/**
 * Created by Omer on 3/7/2016.
 */
public class MoveEntity extends Event {

    public static final String TAG = "MoveEntity";

    private Entity target;

    public MoveEntity(Entity target,float x,float y){
        super();
        this.target = target;
        attributes.addAttribute("toX",x);
        attributes.addAttribute("toY",y);
        attributes.addAttribute("tag",TAG);
        setDelay(0);
        setDuration(0);
        setFrequency(0);

    }

    public MoveEntity(Entity target, Vector2 destination){
        this(target,destination.x,destination.y);
    }

    public MoveEntity(EventModel model){
        this(model.actors.getEntity("target"),(float)model.attributes.getNumeric("toX"),(float)model.attributes.getNumeric("toY"));
    }


    @Override
    public void process() {
        target.getAttributes().addAttribute("toX",attributes.getNumeric("toX"));
        target.getAttributes().addAttribute("toY",attributes.getNumeric("toY"));
        setCurrentState(EventState.FINISHED);
    }

    @Override
    public EventModel getModel() {
        EventModel eventModel = new EventModel();
        eventModel.attributes.addAttribute("toX",attributes.getNumeric("toX"));
        eventModel.attributes.addAttribute("toY",attributes.getNumeric("toY"));
        eventModel.actors.addEntity("target",target);
        return eventModel;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void readMessage(String message) {

    }

    public static Trigger getTrigger(){
        return new Trigger(TAG);
    }
}
