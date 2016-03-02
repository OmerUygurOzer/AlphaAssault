package ingame.logic;



import ingame.objects.Entity;

/**
 * Created by Omer on 3/1/2016.
 */
public abstract class Component {
     private Entity user;

     public Entity getUser(){
          return user;
     }
}
