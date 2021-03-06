package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.handlers.controls.Analog;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.handlers.controls.Inputs;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.handlers.controls.InputReceiver;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.utilities.GameMath;


/**
 * Created by Omer on 11/25/2015.
 */
public class AnalogWithGUI extends Analog  implements Renderable,InputReceiver {

    //GAMEPAD TYPE
    //LEFT_ONLY : ONLY LEFT ANALOG
    //RIGHT_ONLY: ONLY RIGHT ANALOG
    //BOTH: RIGHT AND LEFT ANALOGUES
    private int TYPE;

    public static final int LEFT_ONLY = 0;
    public static final int RIGHT_ONLY = 1;
    public static final int BOTH = 2;

    private static final Vector2 GAME_FRAME_CENTER = new Vector2(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);

    //GAME PAD SPRITES
    private BSprite leftButtonSprite;
    private BSprite leftCircleSprite;
    private BSprite rightButtonSprite;
    private BSprite rightCircleSprite;
    private BSprite gameFrameSprite;


    private static final int BUTTON_SIZE = 40;
    private static final int CIRCLE_SIZE = 180;
    private static final int RADIUS = 90;

    private static final Vector2 LEFT_BUTTON_CENTER = new Vector2(100,100);
    private static final Vector2 RIGHT_BUTTON_CENTER = new Vector2(GameGraphics.VIRTUAL_WIDTH-100,100);

    private Vector2 leftCurrentLocation;
    private Vector2 rightCurrentLocation;

    //REFERENCE IDS
    private  short leftButtonId;
    private  short leftCircleId;
    private  short rightButtonId;
    private  short rightCircleId;
    private  short gameFrameId;

    private short referenceId;
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


    public AnalogWithGUI(int _type) {
        super(_type);

        referenceId = GameSystem.obtainReference();
        leftButtonId = GameSystem.obtainReference();
        leftCircleId = GameSystem.obtainReference();
        rightButtonId = GameSystem.obtainReference();
        rightCircleId = GameSystem.obtainReference();
        gameFrameId = GameSystem.obtainReference();

        //LEFT_ONLY
        leftButtonSprite = new BSprite (Resource.getTextureRegions(Resource.ANALOG)[0][2]);
        leftButtonSprite.setSize(BUTTON_SIZE, BUTTON_SIZE);
        leftButtonSprite.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        leftCircleSprite = new BSprite(Resource.getTextureRegions(Resource.ANALOG)[0][3]);
        leftCircleSprite.setSize(CIRCLE_SIZE, CIRCLE_SIZE);
        leftCircleSprite.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        leftCurrentLocation = new Vector2(LEFT_BUTTON_CENTER);

        //RIGHT_ONLY
        rightButtonSprite = new BSprite (Resource.getTextureRegions(Resource.ANALOG)[0][0]);
        rightButtonSprite.setSize(BUTTON_SIZE, BUTTON_SIZE);
        rightButtonSprite.setCenter(RIGHT_BUTTON_CENTER.x, RIGHT_BUTTON_CENTER.y);
        rightCircleSprite = new BSprite(Resource.getTextureRegions(Resource.ANALOG)[0][1]);
        rightCircleSprite.setSize(CIRCLE_SIZE, CIRCLE_SIZE);
        rightCircleSprite.setCenter(RIGHT_BUTTON_CENTER.x, RIGHT_BUTTON_CENTER.y);
        rightCurrentLocation = new Vector2(RIGHT_BUTTON_CENTER);

        //GAME VIEW FRAME
        gameFrameSprite = new BSprite(Resource.getTextureRegions(Resource.GAME_FRAME)[0][0]);
        gameFrameSprite.setSize(GameGraphics.VIRTUAL_HEIGHT,GameGraphics.VIRTUAL_HEIGHT);
        gameFrameSprite.setCenter(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);


    }

    @Override
    public void addToRenderState() {
        if(TYPE == LEFT_ONLY || TYPE == BOTH) {
            RenderStateManager.updatingStatePointer.addElement(viewType, leftButtonId, RenderState.DEPTH_GAME_SCREEN_BASE, leftButtonSprite);
            RenderStateManager.updatingStatePointer.addElement(viewType, leftCircleId, RenderState.DEPTH_GAME_SCREEN_BASE,leftCircleSprite);
        }
        if(TYPE == RIGHT_ONLY || TYPE == BOTH) {
            RenderStateManager.updatingStatePointer.addElement(viewType, rightButtonId,RenderState.DEPTH_GAME_SCREEN_BASE, rightButtonSprite);
            RenderStateManager.updatingStatePointer.addElement(viewType, rightCircleId,RenderState.DEPTH_GAME_SCREEN_BASE, rightCircleSprite);
        }
        RenderStateManager.updatingStatePointer.addElement(viewType, gameFrameId, RenderState.DEPTH_GAME_SCREEN_BASE, gameFrameSprite);
    }

    @Override
    public void removeFromRenderState() {

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
    public void receiveInput() {

        if(TYPE == LEFT_ONLY || TYPE == BOTH) {
            updateLeft();
        }
        if(TYPE == RIGHT_ONLY || TYPE == BOTH) {
            updateRight();
        }
    }

    @Override
    protected void updateLeft(){
        for (Long key : Inputs.getInputs().keySet()){
            float inputX = Inputs.getInputs().get(key).x;
            float inputY = Inputs.getInputs().get(key).y;
            double distance = GameMath.getDistance(inputX,inputY, LEFT_BUTTON_CENTER.x,LEFT_BUTTON_CENTER.y);
            if( distance<= RADIUS){
                leftCurrentLocation.x = inputX;
                leftCurrentLocation.y = inputY;
                set(LEFT_ANALOG,distance*1/RADIUS);
                set(LEFT_ROTATION,GameMath.getAngle(inputX,inputY,LEFT_BUTTON_CENTER.x,LEFT_BUTTON_CENTER.y));
                set(LEFT_ACTIVE,true);
                leftButtonSprite.setCenter(leftCurrentLocation.x, leftCurrentLocation.y);
                RenderStateManager.updatingStatePointer.updateElement(leftButtonId,RenderState.DEPTH_GAME_SCREEN_BASE,leftButtonSprite);
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
        RenderStateManager.updatingStatePointer.updateElement(leftButtonId,RenderState.DEPTH_GAME_SCREEN_BASE,leftButtonSprite);

    }

    @Override
    protected void updateRight(){
        for (Long key : Inputs.getInputs().keySet()){
            float inputX = Inputs.getInputs().get(key).x;
            float inputY = Inputs.getInputs().get(key).y;
            double distance = GameMath.getDistance(inputX,inputY, RIGHT_BUTTON_CENTER.x,RIGHT_BUTTON_CENTER.y);
            if( distance<= RADIUS){
                rightCurrentLocation.x = inputX;
                rightCurrentLocation.y = inputY;
                set(RIGHT_ANALOG,distance*1/RADIUS);
                set(RIGHT_ROTATION, GameMath.getAngle(inputX,inputY,RIGHT_BUTTON_CENTER.x,RIGHT_BUTTON_CENTER.y));
                set(RIGHT_ACTIVE,true);
                rightButtonSprite.setCenter(rightCurrentLocation.x, rightCurrentLocation.y);
                RenderStateManager.updatingStatePointer.updateElement(rightButtonId,RenderState.DEPTH_GAME_SCREEN_BASE,rightButtonSprite);
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
        RenderStateManager.updatingStatePointer.updateElement(rightButtonId,RenderState.DEPTH_GAME_SCREEN_BASE,rightButtonSprite);

    }

}
