package com.boomer.alphaassault.gameworld.units;


import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.gamelogic.Player;
import com.boomer.alphaassault.gameworld.gamelogic.buffs.Buff;
import com.boomer.alphaassault.gameworld.units.skills.Skill;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.gameworld.gamelogic.Updateable;


import java.util.*;

/**
 * Created by Omer on 11/24/2015.
 */
public abstract class Unit extends Entity implements Updateable,Renderable{

    //CONSTANTS
    protected static final float UNIT_SIZE = 40;
    public static final int MAX_SPEED = 10;


    //MECHANICAL/GRAPHICAL DETAILS
    protected double facingAngle;
    private long referenceId;
    protected int viewType;
    protected BAnimation bAnimation;
    protected Player player;

    //TYPE PROPERTIES
    //COMMON
    public static final int UNIT_RADIUS = 6;

    //ENGINEER
    public static final int ENGINEER_HP = 6;
    public static final int ENGINEER_RANGE = 6;
    public static final int ENGINEER_SIGHT = 10;
    public static final int ENGINEER_FIRE_SPEED = 3;
    public static final int ENGINEER_DAMAGE = 3;
    public static final int ENGINEER_MOVEMENT_SPEED = 8;
    public static final int ENGINEER_AMMO_S1 = 3;
    public static final int ENGINEER_AMMO_S2 = 3;
    //COMMANDO
    public static final int COMMANDO_HP = 10;
    public static final int COMMANDO_RANGE = 12;
    public static final int COMMANDO_SIGHT = 10;
    public static final int COMMANDO_FIRE_SPEED = 1;
    public static final int COMMANDO_DAMAGE = 12;
    public static final int COMMANDO_MOVEMENT_SPEED = 8;
    public static final int COMMANDO_AMMO_S1 = 1;
    public static final int COMMANDO_AMMO_S2 = 0;
    //MEDIC
    public static final int MEDIC_HP = 8;
    public static final int MEDIC_RANGE = 6;
    public static final int MEDIC_SIGHT = 8;
    public static final int MEDIC_FIRE_SPEED = 3;
    public static final int MEDIC_DAMAGE = 3;
    public static final int MEDIC_MOVEMENT_SPEED = 12;
    public static final int MEDIC_AMMO_S1 = 2;
    public static final int MEDIC_AMMO_S2 = 1;

    //IN-GAME DETAILS
    protected int HP;
    private int team;
    protected int damage;
    protected int range;
    protected int baseMovementSpeed;
    protected int adjustedMovementSpeed;
    protected int sight;

    protected List<Buff> activeBuffs;
    protected List<Buff> expiredBuffs;
    protected List<Skill> skills;
    protected Map<Integer, Skill.Supply> supplies;

    protected boolean invisibility;


    protected Unit(int _team, Vector2 _center){
        super(_center);
        Random RANDOM = new Random();
        //BASIC COMMON PROPERTIES
        facingAngle = RANDOM.nextInt((359 - 0) + 1) + 0;
        team = _team;
        radius = UNIT_RADIUS;
        invisibility = false;
        adjustedMovementSpeed = 0;

        //BUFFS
        activeBuffs = new ArrayList<Buff>();
        expiredBuffs = new ArrayList<Buff>();

        //SKILLS
        skills = new ArrayList<Skill>();

        //AMMOS
        supplies = new HashMap<Integer, Skill.Supply>();
    }

    public void setPlayer(Player _player){player = _player;}
    public Player getPlayer(){return player;}
    public boolean isInvisible(){return invisibility;}
    public boolean isAlive() {return (HP>0);}
    public int getHP(){return HP;}
    public int getTeam(){return team;}

    //UPDATEABLE
    @Override
    public void update(float _deltaTime) {
        for(Skill skill :  skills){
            skill.update();
        }

        for(Buff buff : activeBuffs){
            buff.update(_deltaTime);
            if(buff.isExpired()){
                expiredBuffs.add(buff);
            }
        }

        for(Buff buff :  expiredBuffs){
            buff.deflict(this);
        }

        expiredBuffs.clear();

    }
    //RENDERABLE
    @Override
    public void addToRenderState() {RenderStateManager.addElement(viewType, referenceId, RenderState.DEPTH_SURFACE, bAnimation);}

    @Override
    public void removeFromRenderState() {
        RenderStateManager.removeElement(referenceId,RenderState.DEPTH_SURFACE);
    }

    @Override
    public long getReferenceID() {return referenceId;}
    @Override
    public void setViewType(int _viewType) {viewType = _viewType;}
    @Override
    public void setReferenceID(long _referenceId) {
    referenceId = _referenceId;
    }


    public abstract void resupply();
    public abstract void move(float _deltaTime,float _x,float _y,double _angle);

    //SUPPLIES
    protected void setSupplies(int _key,Skill.Supply supply){supplies.put(_key,supply);}
    public Map<Integer,Skill.Supply> getSupplies(){return supplies;}

    //BUFFS
    public void addBuff(Buff _buff){activeBuffs.add(_buff);}
    public void removeBuff(Buff _buff){activeBuffs.remove(_buff);}
    public List<Buff> getActiveBuffs(){return activeBuffs;}

    //SKILLS
    protected void addSkill(Skill _skill){skills.add(_skill);}
    public void use(int _key){skills.get(_key).use(this);}
    public void use(int _key,Location _location){skills.get(_key).use(_location);}
    public void use(int _key,Unit _unit){skills.get(_key).use(_unit);}
    public List<Skill> getSkillSet(){return skills;}

    //MOVEMENT ADJUSTMENTS
    public void adjustMovementSpeed(int _adjustment){adjustedMovementSpeed += _adjustment;}
    public int getBaseMovementSpeed(){return baseMovementSpeed;}
    public int getMovementSpeed(){return baseMovementSpeed + adjustedMovementSpeed;}

}
