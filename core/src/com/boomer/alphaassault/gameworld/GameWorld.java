package com.boomer.alphaassault.gameworld;

import com.badlogic.gdx.graphics.Camera;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.gamelogic.Player;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.gameworld.gamelogic.Updateable;
import com.boomer.alphaassault.handlers.RenderStateManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 11/24/2015.
 */

    /*
    GAMEWORLD:  ALL IN-GAME OBJECTS ARE COLLECTED HERE
     */
public class GameWorld implements Updateable,Renderable{

    private Map gameMap;
    private long baseReference;
    private Player player;
    private Camera camera;
    private List<Entity> entities;

    public GameWorld(Camera _camera){
        entities = new ArrayList<Entity>();
        gameMap = new Map(Map.SIZE_MEDIUM,this);
        camera = _camera;

    }

    public void addPlayer(Player _player){
        player = _player;
        player.setMap(gameMap);
    }

    public void addEntity(Entity _entity){
        entities.add(_entity);
        if(_entity instanceof Renderable){
            ((Renderable) _entity).addToRenderState();
        }
    }

    public void removeEntity(Entity _entity){
        if(_entity instanceof Renderable){
            RenderStateManager.removeElement(_entity.getReferenceId(),_entity.getDepth());
        }
        entities.remove(_entity);
    }

    @Override
    public void addToRenderState() {
        gameMap.addToRenderState();
        player.addToRenderState();
    }

    @Override
    public void removeFromRenderState() {

    }

    @Override
    public long getReferenceID() {
        return baseReference;
    }

    @Override
    public void setReferenceID(long _referenceId) {
        baseReference = _referenceId;
        gameMap.setReferenceID(baseReference + 1000);
    }

    @Override
    public void setViewType(int _viewType) {
        gameMap.setViewType(_viewType);
    }

    @Override
    public void update(float _deltaTime) {
        player.update(_deltaTime);
    }

}
