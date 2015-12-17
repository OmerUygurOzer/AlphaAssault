package com.boomer.alphaassault.GUI;

import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.handlers.controls.Inputs;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.handlers.controls.InputReceiver;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.graphics.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public class Analog extends Controller implements Renderable,InputReceiver {

    //GAMEPAD TYPE
    //LEFT_ONLY : ONLY LEFT ANALOG
    //RIGHT_ONLY: ONLY RIGHT ANALOG
    //BOTH: RIGHT AND LEFT ANALOGUES
    private int TYPE;

    public static final int LEFT_ONLY = 0;
    public static final int RIGHT_ONLY = 1;
    public static final int BOTH = 2;

    private static final Location GAME_FRAME_CENTER = new Location(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);

    //GAME PAD SPRITES
    private BSprite leftButtonSprite;
    private BSprite leftCircleSprite;
    private BSprite rightButtonSprite;
    private BSprite rightCircleSprite;
    private BSprite gameFrameSprite;


    private static final int BUTTON_SIZE = 40;
    private static final int CIRCLE_SIZE = 180;
    private static final int RADIUS = 90;

    private static final Location LEFT_BUTTON_CENTER = new Location(100,100);
    private static final Location RIGHT_BUTTON_CENTER = new Location(GameGraphics.VIRTUAL_WIDTH-100,100);

    private Location leftCurrentLocation;
    private Location rightCurrentLocation;

    //REFERENCE IDS
    private  long leftButtonId;
    private  long leftCircleId;
    private  long rightButtonId;
    private  long rightCircleId;
    private  long gameFrameId;

    private long referenceId;
    private int viewType;

    private boolean leftActive;
    private boolean rightActive;

    //INPUT MAPPING
    public static final int LEFT_ANALOG    = 0;
    public static final int LEFT_ROTATION  = 1;
    public static final int LEFT_ACTIVE    = 2;
    public static final int RIGHT_ANALOG   = 3;
    public static final int RIGHT_ROTATION = 4;
    public static final int RIGHT_ACTIVE   = 5;


    public Analog(int _type) {
        super();


        //LEFT_ONLY
        leftButtonSprite = new BSprite (Resource.getTextureRegions(Resource.ANALOG)[0][2]);
        leftButtonSprite.setSize(BUTTON_SIZE, BUTTON_SIZE);
        leftButtonSprite.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        leftCircleSprite = new BSprite(Resource.getTextureRegions(Resource.ANALOG)[0][3]);
        leftCircleSprite.setSize(CIRCLE_SIZE, CIRCLE_SIZE);
        leftCircleSprite.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        leftCurrentLocation = new Location(LEFT_BUTTON_CENTER);
        leftActive = false;

        //RIGHT_ONLY
        rightButtonSprite = new BSprite (Resource.getTextureRegions(Resource.ANALOG)[0][0]);
        rightButtonSprite.setSize(BUTTON_SIZE, BUTTON_SIZE);
        rightButtonSprite.setCenter(RIGHT_BUTTON_CENTER.x, RIGHT_BUTTON_CENTER.y);
        rightCircleSprite = new BSprite(Resource.getTextureRegions(Resource.ANALOG)[0][1]);
        rightCircleSprite.setSize(CIRCLE_SIZE, CIRCLE_SIZE);
        rightCircleSprite.setCenter(RIGHT_BUTTON_CENTER.x, RIGHT_BUTTON_CENTER.y);
        rightCurrentLocation = new Location(RIGHT_BUTTON_CENTER);
        rightActive = false;

        //GAME VIEW FRAME
        gameFrameSprite = new BSprite(Resource.getTextureRegions(Resource.GAME_FRAME)[0][0]);
        gameFrameSprite.setSize(GameGraphics.VIRTUAL_HEIGHT,GameGraphics.VIRTUAL_HEIGHT);
        gameFrameSprite.setCenter(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);


        TYPE = _type;

    }

    @Override
    public void addToRenderState() {
        if(TYPE == LEFT_ONLY || TYPE == BOTH) {
            RenderStateManager.addElement(viewType, leftButtonId, RenderState.DEPTH_GAME_SCREEN_BASE, leftButtonSprite);
            RenderStateManager.addElement(viewType, leftCircleId, RenderState.DEPTH_GAME_SCREEN_BASE,leftCircleSprite);
        }
        if(TYPE == RIGHT_ONLY || TYPE == BOTH) {
            RenderStateManager.addElement(viewType, rightButtonId,RenderState.DEPTH_GAME_SCREEN_BASE, rightButtonSprite);
            RenderStateManager.addElement(viewType, rightCircleId,RenderState.DEPTH_GAME_SCREEN_BASE, rightCircleSprite);
        }
        RenderStateManager.addElement(viewType, gameFrameId, RenderState.DEPTH_GAME_SCREEN_BASE, gameFrameSprite);
    }

    @Override
    public void removeFromRenderState() {

    }


    @Override
    public long getReferenceID() {
        return referenceId;
    }

    @Override
    public void setReferenceID(long _referenceId) {
        referenceId = _referenceId;
        leftButtonId = referenceId;
        leftCircleId = referenceId  +1;
        rightButtonId = referenceId +2;
        rightCircleId = referenceId +3;
        gameFrameId = referenceId   +4;
    }

    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }


    @Override
    public void receiveInput() {

        if(TYPE == LEFT_ONLY || TYPE == BOTH) {
            updateLeft();
        }
        if(TYPE == RIGHT_ONLY || TYPE == BOTH) {
            updateRight();
        }
    }

    private void updateLeft(){
        for (Long key : Inputs.getInputs().keySet()){
            float inputX = Inputs.getInputs().get(key).x;
            float inputY = Inputs.getInputs().get(key).y;
            double distance = Location.getDistance(inputX,inputY, LEFT_BUTTON_CENTER.x,LEFT_BUTTON_CENTER.y);
            if( distance<= RADIUS){
                leftCurrentLocation.x = inputX;
                leftCurrentLocation.y = inputY;
                set(LEFT_ANALOG,distance*1/RADIUS);
                set(LEFT_ROTATION,Location.getAngle(inputX,inputY,LEFT_BUTTON_CENTER.x,LEFT_BUTTON_CENTER.y));
                set(LEFT_ACTIVE,true);
                leftButtonSprite.setCenter(leftCurrentLocation.x, leftCurrentLocation.y);
                RenderStateManager.updatingState.updateElement(leftButtonId,RenderState.DEPTH_GAME_SCREEN_BASE,leftButtonSprite);
                leftActive = true;
                return;
            }


        }
        set(LEFT_ANALOG,0);
        set(LEFT_ROTATION,0);
        set(LEFT_ACTIVE,false);
        leftActive = false;
        leftCurrentLocation.x = LEFT_BUTTON_CENTER.x;
        leftCurrentLocation.y = LEFT_BUTTON_CENTER.y;
        leftButtonSprite.setCenter(leftCurrentLocation.x, leftCurrentLocation.y);
        RenderStateManager.updatingState.updateElement(leftButtonId,RenderState.DEPTH_GAME_SCREEN_BASE,leftButtonSprite);

    }

    private void updateRight(){
        for (Long key : Inputs.getInputs().keySet()){
            float inputX = Inputs.getInputs().get(key).x;
            float inputY = Inputs.getInputs().get(key).y;
            double distance = Location.getDistance(inputX,inputY, RIGHT_BUTTON_CENTER.x,RIGHT_BUTTON_CENTER.y);
            if( distance<= RADIUS){
                rightCurrentLocation.x = inputX;
                rightCurrentLocation.y = inputY;
                set(RIGHT_ANALOG,distance*1/RADIUS);
                set(RIGHT_ROTATION,Location.getAngle(inputX,inputY,RIGHT_BUTTON_CENTER.x,RIGHT_BUTTON_CENTER.y));
                set(RIGHT_ACTIVE,true);
                rightButtonSprite.setCenter(rightCurrentLocation.x, rightCurrentLocation.y);
                RenderStateManager.updatingState.updateElement(rightButtonId,RenderState.DEPTH_GAME_SCREEN_BASE,rightButtonSprite);
                leftActive = true;
                return;
            }


        }
        set(RIGHT_ANALOG,0);
        set(RIGHT_ROTATION,0);
        set(RIGHT_ACTIVE,false);
        rightActive = false;
        rightCurrentLocation.x = RIGHT_BUTTON_CENTER.x;
        rightCurrentLocation.y = RIGHT_BUTTON_CENTER.y;
        rightButtonSprite.setCenter(rightCurrentLocation.x, rightCurrentLocation.y);
        RenderStateManager.updatingState.updateElement(rightButtonId,RenderState.DEPTH_GAME_SCREEN_BASE,rightButtonSprite);

    }

}
