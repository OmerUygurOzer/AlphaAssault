package com.boomer.alphaassault.gameworld;

import com.badlogic.gdx.graphics.Camera;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.map.Map;
import com.boomer.alphaassault.gameworld.map.MapIO;
import com.boomer.alphaassault.gameworld.players.AI;
import com.boomer.alphaassault.gameworld.players.Human;
import com.boomer.alphaassault.gameworld.players.Player;
import com.boomer.alphaassault.gameworld.projectiles.Bullet;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.gameworld.gamelogic.Updateable;
import com.boomer.alphaassault.handlers.events.EventHandler;
import  com.boomer.alphaassault.memoryutils.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 11/24/2015.
 */

    /*
    GAMEWORLD:  ALL IN-GAME OBJECTS ARE COLLECTED HERE
     */
public class GameWorld implements Updateable,Renderable{

    private short baseReference;

    private Map gameMap;

    private Camera camera;

    private Human contextPlayer;


    private List<Player> clientPlayers;
    private List<Entity> entities;
    private AI computerPlayer;


    private EventHandler eventHandler;


    public Pool<Bullet> bulletPool;

    public int viewType;

    private List<Entity> additions;
    private List<Entity> removals;

    public GameWorld(Camera _camera){
        clientPlayers = new ArrayList<Player>();
        computerPlayer = new AI(this);

        entities = new ArrayList<Entity>();
        removals = new ArrayList<Entity>();
        additions = new ArrayList<Entity>();

        final GameWorld worldPointer = this;
        bulletPool = new Pool<Bullet>() {
            @Override
            protected Bullet newObject() {
                return new Bullet(worldPointer);
            }
        };


        gameMap = new Map(Map.SIZE_MEDIUM,this);
        //MapIO.saveMap(gameMap,"random1");
        //MapIO.loadMap(gameMap,"random1");
        camera = _camera;



    }

    public void setEventHandler(EventHandler _eventHandler){
        eventHandler = _eventHandler;
    }

    public EventHandler getEventHandler(){return eventHandler;}

    //public

    public void addPlayer(Human _player){
        contextPlayer = _player;
    }



    public Map getGameMap(){return gameMap;}

    @Override
    public void addToRenderState() {
        gameMap.addToRenderState();
        contextPlayer.addToRenderState();
    }

    @Override
    public void removeFromRenderState() {

    }

    @Override
    public short getReferenceID() {
        return baseReference;
    }



    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
        gameMap.setViewType(_viewType);
    }

    @Override
    public void update(float _deltaTime) {
        contextPlayer.update(_deltaTime);
        doEntityAddition();
        doEntityUpdates(_deltaTime);
        handleCollisions();
        doEntityRemoval();

    }

    public void doEntityAddition(){
        for(Entity entity : additions){
            if(entity instanceof Renderable){
                ((Renderable)entity).addToRenderState();
            }
            entities.add(entity);
        }
        additions.clear();

    }


    public void doEntityUpdates(float _deltaTime){
        for(Entity entity : entities){
            entity.update(_deltaTime);
        }
    }

    public void handleCollisions(){
        for(Entity hitter : entities){
           for(Entity receiver : entities){
               if(hitter != receiver){
                   if (Entity.doesCollide(hitter,receiver)){
                       hitter.uponCollision(receiver);
                   }
               }
           }

        }
    }

    public void doEntityRemoval(){
        for(Entity entity : entities){
            if(entity.isRemoved()){
                if(entity instanceof Bullet){
                    bulletPool.free((Bullet)entity);
                }


                removals.add(entity);
            }
        }

        for(Entity entity : removals){
            removeEntity(entity);
        }
        removals.clear();
    }

    public void addEntity(Entity _entity){
        additions.add(_entity);
    }

    public void removeEntity(Entity _entity){
        if(_entity instanceof Renderable){
            ((Renderable)_entity).removeFromRenderState();
        }
        entities.remove(_entity);
    }



}
