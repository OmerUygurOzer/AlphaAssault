package com.boomer.alphaassault.gameworld.players;

import com.badlogic.gdx.graphics.Texture;
import com.boomer.alphaassault.GUI.AnalogWithGUI;
import com.boomer.alphaassault.GUI.ConsoleWithGUI;
import com.boomer.alphaassault.GUI.Hud;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.skills.Skill;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.cameras.SightCamera;
import com.boomer.alphaassault.handlers.controls.Analog;
import com.boomer.alphaassault.handlers.controls.Console;
import com.boomer.alphaassault.handlers.controls.Controllable;
import com.boomer.alphaassault.resources.Resource;


/**
 * Created by Omer on 12/24/2015.
 */
public class Human extends Player implements Renderable,Controllable{

    private short referenceId;

    private String name;
    private Texture icon;

    private int viewType;

    private SightCamera camera;

    //CONTROLS
    private Analog analog;
    private Console console;
    private Hud hud;

    //IN-GAME
    private Unit playerUnit;

    /********************/
    /*
    CONSTRUCTOR
    */
    /********************/

    public Human(SightCamera _camera,GameWorld _world,Unit _unit) {
        super(_world);
        camera = _camera;
        camera.setSight(_unit.getSight());
        playerUnit = _unit;
        playerUnit.setPlayer(this);
        referenceId = GameSystem.obtainReference();
    }

    //NAME AND ICON
    public void setName(String _name){name = _name;}
    public String getName(){return name;}
    public void setIcon(Texture _icon){icon = _icon;}
    public Texture getIcon(){return icon;}

    public void move(float _deltaTime){
/*
        if(analog.get(AnalogWithGUI.LEFT_ACTIVE).valueBoolean) {
            double power = analog.get(AnalogWithGUI.LEFT_ANALOG).valueDouble * playerUnit.getMovementSpeed() / Unit.MAX_SPEED;
            double angle = analog.get(AnalogWithGUI.LEFT_ROTATION).valueDouble;
            float x =  camera.position.x + (float) (Math.sin(Math.toRadians(angle)) * power);
            x = x < world.getGameMap().getWidth() ? x : world.getGameMap().getWidth();
            x = x < 0 ? 0 : x;
            float y = camera.position.y + (float) (Math.cos(Math.toRadians(angle)) * power);
            y = y < world.getGameMap().getHeight() ? y : world.getGameMap().getHeight();
            y = y < 0 ? 0 : y;

            if(world.getGameMap().isMoveable(x,y)){
                camera.position.set(x, y, camera.position.z);
                playerUnit.move(_deltaTime, x, y, angle);
            }

        }
*/
    }



    public Unit getPlayerUnit(){return playerUnit;}
    public void setPlayerUnit(Unit _unit){addControlledUnit(_unit);playerUnit = _unit;}

    @Override
    public void addToRenderState() {
        playerUnit.addToRenderState();

    }

    @Override
    public void removeFromRenderState() {
        playerUnit.removeFromRenderState();
    }

    @Override
    public short getReferenceID() {
        return referenceId;
    }


    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
        playerUnit.setViewType(viewType);
    }

    @Override
    public void update(float _deltaTime) {
        control(_deltaTime);
        playerUnit.update(_deltaTime);
    }

    @Override
    public void setAnalog(Analog _analog) {
        analog = _analog;
    }

    @Override
    public void setConsole(Console _console) {
        console = _console;
        for(Skill skill : playerUnit.getSkillSet()){
            ((ConsoleWithGUI)console).addButton(skill.getKey(), Resource.getTextureRegions(Resource.BUTTONS),skill.getIcon());
        }
        ((ConsoleWithGUI)console).addToRenderState();

    }

    public void setHud(Hud _hud){hud = _hud; hud.setPlayer(this);}
    public Hud getHud(){return hud;}

    @Override
    public void control(float _deltaTime) {
        move(_deltaTime);
        for(Skill skill : playerUnit.getSkillSet()){
            if(console.get(skill.getKey()).valueDouble==ConsoleWithGUI.PRESSED){
                switch (skill.getTargetType()) {
                    case Skill.TARGET_TYPE_SELF:
                        playerUnit.use(skill.getKey());
                        break;
                    case Skill.TARGET_TYPE_POINT:
                        break;
                    case Skill.TARGET_TYPE_UNIT:
                        break;
                    case Skill.TARGET_TYPE_ANGLE:
                        playerUnit.use(skill.getKey(),(float)playerUnit.getFacingAngle());
                        break;
                    default:
                        break;
                }

            }
        }
    }
}
