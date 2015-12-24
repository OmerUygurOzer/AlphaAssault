package com.boomer.alphaassault.gameworld.gamelogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.GUI.Analog;
import com.boomer.alphaassault.GUI.Console;
import com.boomer.alphaassault.GUI.Hud;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.assaulttrooper.AssaultTrooper;
import com.boomer.alphaassault.gameworld.units.skills.Skill;
import com.boomer.alphaassault.graphics.cameras.SightCamera;
import com.boomer.alphaassault.handlers.controls.Controllable;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.graphics.Renderable;


/**
 * Created by Omer on 12/6/2015.
 */
public class Player implements Updateable,Renderable,Controllable{
    public static final int ASSAULT_TROOPER = 0;
    public static final int ENGINEER        = 1;
    public static final int COMMANDO        = 2;
    public static final int MEDIC           = 3;

    private short referenceId;

    private String name;
    private Texture icon;

    private int viewType;

    private SightCamera camera;
    //GUI
    private Controller analog;
    private Controller console;
    private Hud hud;

    //IN-GAME
    private Unit playerUnit;
    private GameWorld world;

    /********************/
    /*
    CONSTRUCTOR
    */
    /********************/

    public Player(SightCamera _camera) {
        camera = _camera;
        referenceId = GameSystem.obtainReference();
    }

    //NAME AND ICON
    public void setName(String _name){name = _name;}
    public String getName(){return name;}
    public void setIcon(Texture _icon){icon = _icon;}
    public Texture getIcon(){return icon;}

    public void move(float _deltaTime){
        if(analog.get(Analog.LEFT_ACTIVE).valueBoolean) {
            double power = analog.get(Analog.LEFT_ANALOG).valueDouble * playerUnit.getMovementSpeed() / Unit.MAX_SPEED;
            double angle = analog.get(Analog.LEFT_ROTATION).valueDouble;
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
    }

    public void setWorld(GameWorld _world){
        world = _world;
    }

    public void setRole(int _role){
        switch (_role){
            case ASSAULT_TROOPER:
                playerUnit = new AssaultTrooper(GameSystem.TEAM_BLUE,new Vector2(Math.round(camera.position.x),Math.round(camera.position.y)),world);
                playerUnit.setViewType(viewType);
                playerUnit.setPlayer(this);
                camera.setSight(AssaultTrooper.ASSAULT_TROOPER_SIGHT);

                //MAP SKILLS TO CONSOLE BUTTONS
                for(Skill skill : playerUnit.getSkillSet()){
                    ((Console)console).addButton(skill.getKey(), Resource.getTextureRegions(Resource.BUTTONS),skill.getIcon());
                }
                ((Console)console).addToRenderState();





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

    public Unit getPlayerUnit(){return playerUnit;}


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

    public void setHud(Hud _hud){hud = _hud; hud.setPlayer(this);}
    public Hud getHud(){return hud;}

    @Override
    public void control(float _deltaTime) {
        move(_deltaTime);
        for(Skill skill : playerUnit.getSkillSet()){
            if(console.get(skill.getKey()).valueDouble==Console.PRESSED){
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
