package com.boomer.alphaassault.gameworld;

import com.badlogic.gdx.graphics.Camera;
import com.boomer.alphaassault.gameworld.gamelogic.Player;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.utilities.Renderable;
import com.boomer.alphaassault.utilities.Updateable;

/**
 * Created by Omer on 11/24/2015.
 */

    /*
    GAMEWORLD:  ALL IN-GAME OBJECTS ARE COLLECTED HERE
     */
public class GameWorld implements Updateable,Renderable{

    Controller controller;
    private Map gameMap;
    private long baseReference;
    private Player player;
    private Camera camera;

    public GameWorld(Camera _camera){
        gameMap = new Map(Map.SIZE_SMALL);
        camera = _camera;

    }

    public void setController(Controller _controller){
        controller = _controller;
        player.setController(controller);
    }

    public void addPlayer(Player _player){
        player = _player;
        player.setMap(gameMap);
    }

    @Override
    public void addToRenderState() {
        gameMap.addToRenderState();
        player.addToRenderState();
    }

    @Override
    public long getReferenceID() {
        return baseReference;
    }

    @Override
    public void setReferenceID(long _referenceId) {
        baseReference = _referenceId;
    }

    @Override
    public void setViewType(int _viewType) {
        gameMap.setViewType(_viewType);
        player.setViewType(_viewType);
    }

    @Override
    public void update(float _deltaTime) {
        player.move(_deltaTime);
        player.update(_deltaTime);
    }
}
