package com.boomer.alphaassault.gameworld.units.assaulttrooper;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.skills.Flashbang;
import com.boomer.alphaassault.gameworld.units.skills.Run;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/24/2015.
 */
public class AssaultTrooper extends Unit implements AssaultTrooperSkillSet {

    //ASSAULT TROOPER
    public static final int ASSAULT_TROOPER_HP = 12;
    public static final int ASSAULT_TROOPER_RANGE = 8;
    public static final int ASSAULT_TROOPER_SIGHT = 8;
    public static final int ASSAULT_TROOPER_FIRE_SPEED = 4;
    public static final int ASSAULT_TROOPER_DAMAGE = 4;
    public static final int ASSAULT_TROOPER_MOVEMENT_SPEED = 8;
    public static final int ASSAULT_TROOPER_AMMO_S1 = 2;
    public static final int ASSAULT_TROOPER_AMMO_S2 = 0;


    //SKILLS
    Flashbang flashbang  = new Flashbang();
    Run run = new Run();


    public AssaultTrooper(int _team, Location _location) {
        super(_team, _location);
        setLocation(_location.x,_location.y);

        //CALCULATE/LOAD ANIMATIONS
        TextureRegion[][] framesAll = TextureRegion.split(Resource.getTexture(Resource.TEXTURE_ASSAULT_TROOPER),138,192);
        TextureRegion[] framesWalkRight = new TextureRegion[6];
        TextureRegion[] framesWalkLeft = new TextureRegion[6];
        TextureRegion[] framesWalkUp = new TextureRegion[6];
        TextureRegion[] framesWalkDown = new TextureRegion[6];
        TextureRegion[] framesWalkUpRight = new TextureRegion[6];
        TextureRegion[] framesWalkUpLeft = new TextureRegion[6];
        TextureRegion[] framesWalkDownRight = new TextureRegion[6];
        TextureRegion[] framesWalkDownLeft = new TextureRegion[6];
        for(int i=0;i<6;i++){
            framesWalkRight[i] = framesAll[6][i];
        }
        for(int i=0;i<6;i++){
            framesWalkLeft[i] = framesAll[2][i];
        }
        for(int i=0;i<6;i++){
            framesWalkUp[i] = framesAll[4][i];
        }
        for(int i=0;i<6;i++){
            framesWalkDown[i] = framesAll[0][i];
        }
        for(int i=0;i<6;i++){
            framesWalkUpRight[i] = framesAll[5][i];
        }
        for(int i=0;i<6;i++){
            framesWalkUpLeft[i] = framesAll[3][i];
        }
        for(int i=0;i<6;i++){
            framesWalkDownRight[i] = framesAll[7][i];
        }
        for(int i=0;i<6;i++){
            framesWalkDownLeft[i] = framesAll[1][i];
        }
        animationWalkRight = new Animation(1f/120f,framesWalkRight);
        animationWalkLeft = new Animation(1f/120f,framesWalkLeft);
        animationWalkUp = new Animation(1f/120f,framesWalkUp);
        animationWalkDown = new Animation(1f/120f,framesWalkDown);
        animationWalkUpRight = new Animation(1f/120f,framesWalkUpRight);
        animationWalkUpLeft = new Animation(1f/120f,framesWalkUpLeft);
        animationWalkDownRight = new Animation(1f/120f,framesWalkDownRight);
        animationWalkDownLeft = new Animation(1f/120f,framesWalkDownLeft);
        Rotation facingRotation = getRotation(facingAngle);

        currentSprite = new Sprite();
        switch(facingRotation){
            case RIGHT:
                currentSprite.set(new Sprite(animationWalkRight.getKeyFrame(0f)));
                break;
            case LEFT:
                currentSprite.set(new Sprite(animationWalkLeft.getKeyFrame(0f)));
                break;
            case UP:
                currentSprite.set(new Sprite(animationWalkUp.getKeyFrame(0f)));
                break;
            case DOWN:
                currentSprite.set(new Sprite(animationWalkDown.getKeyFrame(0f)));
                break;
            case UP_RIGHT:
                currentSprite.set(new Sprite(animationWalkUpRight.getKeyFrame(0f)));
                break;
            case UP_LEFT:
                currentSprite.set(new Sprite(animationWalkUpLeft.getKeyFrame(0f)));
                break;
            case DOWN_RIGHT:
                currentSprite.set(new Sprite(animationWalkDownRight.getKeyFrame(0f)));
                break;
            case DOWN_LEFT:
                currentSprite.set(new Sprite(animationWalkDownLeft.getKeyFrame(0f)));
                break;
            default:
                //DO NOTHING
                break;

        }
        currentSprite.setSize(UNIT_SIZE,UNIT_SIZE);
        currentSprite.setCenter(0f,0f);
    }

    @Override
    public void fire() {

    }

    //ASSAULT TROOPER SKILL SET
    @Override
    public void flashbang() {
    }

    @Override
    public void run() {

    }
    //UNIT
    @Override
    public void resupply() {

    }

    @Override
    public void move(float _deltaTime,float _x, float _y,double _angle) {
        facingAngle = _angle;
        animationTimer += _deltaTime;
        Rotation facingRotation = getRotation(facingAngle);

        switch(facingRotation){
            case RIGHT:
                currentSprite.set(new Sprite(animationWalkRight.getKeyFrame(animationTimer,true)));
                break;
            case LEFT:
                currentSprite.set(new Sprite(animationWalkLeft.getKeyFrame(animationTimer,true)));
                break;
            case UP:
                currentSprite.set(new Sprite(animationWalkUp.getKeyFrame(animationTimer,true)));
                break;
            case DOWN:
                currentSprite.set(new Sprite(animationWalkDown.getKeyFrame(animationTimer,true)));
                break;
            case UP_RIGHT:
                currentSprite.set(new Sprite(animationWalkUpRight.getKeyFrame(animationTimer,true)));
                break;
            case UP_LEFT:
                currentSprite.set(new Sprite(animationWalkUpLeft.getKeyFrame(animationTimer,true)));
                break;
            case DOWN_RIGHT:
                currentSprite.set(new Sprite(animationWalkDownRight.getKeyFrame(animationTimer,true)));
                break;
            case DOWN_LEFT:
                currentSprite.set(new Sprite(animationWalkDownLeft.getKeyFrame(animationTimer,true)));
                break;
            default:
                //DO NOTHING
                break;

        }


        currentSprite.setSize(UNIT_SIZE,UNIT_SIZE);
        currentSprite.setCenter(_x,_y);
        RenderStateManager.updatingState.updateElement(getReferenceID(), RenderState.DEPTH_SURFACE,currentSprite);
    }

    //UPDATEABLE
    @Override
    public void update(float _deltaTime) {
        //super.update();

    }



}
