package ingame.logic;



import ingame.objects.Entity;
import triggers.events.Event;
import triggers.events.EventHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 3/1/2016.
 */
public abstract class Component implements EventHandler.Attachable{
     private Entity user;

     protected List<Event> eventsList;

     protected Component(Entity user){
          this.user = user;
          this.eventsList = new ArrayList<Event>();
     }

     @Override
     public void attach(EventHandler eventHandler) {
          for(Event event: eventsList){
               event.attach(eventHandler);
          }
     }

     public Entity getUser(){
          return user;
     }


}
