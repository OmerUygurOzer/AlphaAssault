package com.boomer.alphaassault.gameworld.gamelogic;

import com.boomer.alphaassault.GUI.Analog;
import com.boomer.alphaassault.GUI.Console;
import com.boomer.alphaassault.gameworld.Map;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.assaulttrooper.AssaultTrooper;
import com.boomer.alphaassault.gameworld.units.skills.Fire;
import com.boomer.alphaassault.gameworld.units.skills.Flashbang;
import com.boomer.alphaassault.gameworld.units.skills.Run;
import com.boomer.alphaassault.graphics.cameras.SightCamera;
import com.boomer.alphaassault.handlers.controls.Controllable;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.settings.GameSettings;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.utilities.Updateable;


/**
 * Created by Omer on 12/6/2015.
 */
public class Player implements Updateable,Renderable,Controllable{
    public static final int ASSAULT_TROOPER = 0;
    public static final int ENGINEER        = 1;
    public static final int COMMANDO        = 2;
    public static final int MEDIC           = 3;

    private static final int PLAYER_REFERENCE = 1;
    private int viewType;

    private SightCamera camera;
    private Controller analog;
    private Controller console;
    private Unit playerUnit;
    private Map map;

    public Player(SightCamera _camera) {
        camera = _camera;
    }



    public void move(float _deltaTime){
        if(analog.get(Analog.LEFT_ACTIVE).valueBoolean) {
            double power = analog.get(Analog.LEFT_ANALOG).valueDouble * playerUnit.getMovementSpeed() / Unit.MAX_SPEED;
            System.out.println(power);
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

    public void setRole(int _role){
        switch (_role){
            case ASSAULT_TROOPER:
                playerUnit = new AssaultTrooper(GameSettings.TEAM_BLUE,new Location(Math.round(camera.position.x),Math.round(camera.position.y)));
                playerUnit.setReferenceID(PLAYER_REFERENCE);
                camera.setSight(AssaultTrooper.ASSAULT_TROOPER_SIGHT);
                ((Console)console).addButton(Fire.KEY, Resource.getTexture(Resource.TEXTURE_BUTTON_BASE));
                ((Console)console).addButton(Run.KEY, Resource.getTexture(Resource.TEXTURE_BUTTON_BASE));
                ((Console)console).addButton(Flashbang.KEY, Resource.getTexture(Resource.TEXTURE_BUTTON_BASE));
                ((Console)console).addToRenderState();

                playerUnit.setViewType(viewType);
                playerUnit.addToRenderState();
                break;
            case ENGINEER:

                break;
            case COMMANDO:

                break;
            case MEDIC:
                break;

            default:
                //DO NOTHING
                break;
        }

    }


    @Override
    public void addToRenderState() {


    }

    @Override
    public long getReferenceID() {
        return PLAYER_REFERENCE;
    }

    @Override
    public void setReferenceID(long _referenceId) {}

    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
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
