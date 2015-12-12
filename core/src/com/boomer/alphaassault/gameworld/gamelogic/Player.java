package com.boomer.alphaassault.gameworld.gamelogic;

import com.boomer.alphaassault.GUI.Analog;
import com.boomer.alphaassault.gameworld.Map;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.assaulttrooper.AssaultTrooper;
import com.boomer.alphaassault.graphics.cameras.SightCamera;
import com.boomer.alphaassault.handlers.controls.Controllable;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.settings.GameSettings;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.utilities.Updateable;


/**
 * Created by Omer on 12/6/2015.
 */
public class Player implements Updateable,Renderable,Controllable{
    private static final int PLAYER_REFERENCE = 1;

    private SightCamera camera;
    private Controller analog;
    private Controller console;
    private Unit playerUnit;
    private Map map;

    public Player(SightCamera _camera) {
        camera = _camera;
        playerUnit = new AssaultTrooper(GameSettings.TEAM_BLUE,new Location(Math.round(camera.position.x),Math.round(camera.position.y)));
        playerUnit.setReferenceID(PLAYER_REFERENCE);
        camera.setSight(AssaultTrooper.ASSAULT_TROOPER_SIGHT);
    }


    public void move(float _deltaTime){
        if(analog.get(Analog.LEFT_ACTIVE).valueBoolean) {
            double power = analog.get(Analog.LEFT_ANALOG).valueDouble;
            double angle = analog.get(Analog.LEFT_ROTATION).valueDouble;
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
    public void setReferenceID(long _referenceId) {}

    @Override
    public void setViewType(int _viewType) {
        playerUnit.setViewType(_viewType);
    }

    @Override
    public void update(float _deltaTime) {
        control(_deltaTime);
        playerUnit.update(_deltaTime);
    }

    @Override
    public void setAnalog(Controller _controller) {
        analog = _controller;
    }

    @Override
    public void setConsole(Controller _controller) {
        console = _controller;
    }

    @Override
    public void control(float _deltaTime) {
        move(_deltaTime);
    }
}
