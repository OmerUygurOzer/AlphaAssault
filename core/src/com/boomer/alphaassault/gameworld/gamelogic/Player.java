package com.boomer.alphaassault.gameworld.gamelogic;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.boomer.alphaassault.GUI.GamePad;
import com.boomer.alphaassault.gameworld.Map;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.assaulttrooper.AssaultTrooper;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.settings.GameSettings;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;
import com.boomer.alphaassault.utilities.Updateable;


/**
 * Created by Omer on 12/6/2015.
 */
public class Player implements Updateable,Renderable{
    private static final int PLAYER_REFERENCE = 1;

    private Camera camera;
    private Controller controller;
    private Unit playerUnit;
    private Map map;

    public Player(Camera _camera) {
        camera = _camera;
        playerUnit = new AssaultTrooper(GameSettings.TEAM_BLUE,new Location(Math.round(camera.position.x),Math.round(camera.position.y)));
        playerUnit.setReferenceID(PLAYER_REFERENCE);
    }

    public void setController(Controller _controller){
        controller = _controller;
    }

    public void move(float _deltaTime){
        if(controller.get(GamePad.LEFT_ACTIVE).valueBoolean) {
            double power = controller.get(GamePad.LEFT_ANALOG).valueDouble;
            double angle = controller.get(GamePad.LEFT_ROTATION).valueDouble;
            float x =  camera.position.x + (float) (Math.sin(Math.toRadians(angle)) * power);
            x = x < map.getWidth() ? x : map.getWidth();
            x = x < 0 ? 0 : x;
            float y = camera.position.y + (float) (Math.cos(Math.toRadians(angle)) * power);
            y = y < map.getHeight() ? y : map.getHeight();
            y = y < 0 ? 0 : y;
            if(map.isMoveable(x,y)){
                camera.position.set(x, y, camera.position.z);
                playerUnit.move(_deltaTime, x, y, angle);
            }

        }
    }

    public void setMap(Map _map){
        map = _map;
    }


    @Override
    public void addToRenderState() {
        playerUnit.addToRenderState();
    }

    @Override
    public long getReferenceID() {
        return PLAYER_REFERENCE;
    }

    @Override
    public void setReferenceID(long _referenceId) {

    }

    @Override
    public void setViewType(int _viewType) {
        playerUnit.setViewType(_viewType);
    }

    @Override
    public void update(float _deltaTime) {
        playerUnit.update(_deltaTime);
    }
}
