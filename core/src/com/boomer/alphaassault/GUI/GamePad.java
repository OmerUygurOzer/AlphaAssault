package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.handlers.controls.Inputs;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.InputReceiver;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public class GamePad extends Controller implements Renderable,InputReceiver {

    //GAMEPAD TYPE
    //LEFT_ONLY : ONLY LEFT_ONLY CONSOLE
    //RIGHT_ONLY: ONLY RIGHT_ONLY CONSOLE
    //BOTH: RIGHT_ONLY AND LEFT_ONLY CONSOLES
    private int TYPE;

    public static final int LEFT_ONLY = 0;
    public static final int RIGHT_ONLY = 1;
    public static final int BOTH = 2;

    private static final Location gameFrameCenter = new Location(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);

    //GAME PAD SPRITES
    private Sprite leftButtonSprite;
    private Sprite leftCircleSprite;
    private Sprite rightButtonSprite;
    private Sprite rightCircleSprite;
    private Sprite gameFrame;


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
    private  long hudId;

    private long referenceId;
    private int viewType;

    private boolean leftActive;
    private boolean rightActive;

    //INPUT MAPPING
    public static final int LEFT_ANALOG = 0;
    public static final int LEFT_ROTATION = 1;
    public static final int RIGHT_ANALOG = 2;
    public static final int RIGHT_ROTATION = 3;


    public GamePad(int _type) {
        super();
        referenceId = System.currentTimeMillis();
        leftButtonId = referenceId;
        leftCircleId = referenceId+1;
        rightButtonId = referenceId+2;
        rightCircleId = referenceId+3;
        hudId = referenceId +4;

        //LEFT_ONLY
        leftButtonSprite = new Sprite (Resource.getTexture(Resource.TEXTURE_LEFT_BUTTON));
        leftButtonSprite.setSize(BUTTON_SIZE, BUTTON_SIZE);
        leftButtonSprite.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        leftCircleSprite = new Sprite(Resource.getTexture(Resource.TEXTURE_LEFT_CIRCLE));
        leftCircleSprite.setSize(CIRCLE_SIZE, CIRCLE_SIZE);
        leftCircleSprite.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        leftCurrentLocation = new Location(LEFT_BUTTON_CENTER);
        leftActive = false;

        //RIGHT_ONLY
        rightButtonSprite = new Sprite (Resource.getTexture(Resource.TEXTURE_RIGHT_BUTTON));
        rightButtonSprite.setSize(BUTTON_SIZE, BUTTON_SIZE);
        rightButtonSprite.setCenter(RIGHT_BUTTON_CENTER.x, RIGHT_BUTTON_CENTER.y);
        rightCircleSprite = new Sprite(Resource.getTexture(Resource.TEXTURE_RIGHT_CIRCLE));
        rightCircleSprite.setSize(CIRCLE_SIZE, CIRCLE_SIZE);
        rightCircleSprite.setCenter(RIGHT_BUTTON_CENTER.x, RIGHT_BUTTON_CENTER.y);
        rightCurrentLocation = new Location(RIGHT_BUTTON_CENTER);
        rightActive = false;

        //GAME HUD VIEW FRAME
        gameFrame = new Sprite(Resource.getTexture(Resource.TEXTURE_HUD_CAM));
        gameFrame.setSize(GameGraphics.VIRTUAL_HEIGHT,GameGraphics.VIRTUAL_HEIGHT);
        gameFrame.setCenter(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);


        TYPE = _type;

    }

    @Override
    public void addToRenderState() {
        if(TYPE == LEFT_ONLY || TYPE == BOTH) {
            RenderStateManager.addElement(viewType, leftButtonId, RenderState.DEPTH_GAME_SCREEN, leftButtonSprite);
            RenderStateManager.addElement(viewType, leftCircleId, RenderState.DEPTH_GAME_SCREEN,leftCircleSprite);
        }
        if(TYPE == RIGHT_ONLY || TYPE == BOTH) {
            RenderStateManager.addElement(viewType, rightButtonId,RenderState.DEPTH_GAME_SCREEN, rightButtonSprite);
            RenderStateManager.addElement(viewType, rightCircleId,RenderState.DEPTH_GAME_SCREEN, rightCircleSprite);
        }
        RenderStateManager.addElement(viewType, hudId, RenderState.DEPTH_GAME_SCREEN,gameFrame);
    }


    @Override
    public long getReferenceID() {
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

    private void updateLeft(){
        for (Long key : Inputs.getInputs().keySet()){
            if(Location.getDistance(Inputs.getInputs().get(key), LEFT_BUTTON_CENTER)<= RADIUS){
                leftCurrentLocation.x = Inputs.getInputs().get(key).x;
                leftCurrentLocation.y = Inputs.getInputs().get(key).y;
                set(LEFT_ANALOG,Location.getDistance(Inputs.getInputs().get(key), LEFT_BUTTON_CENTER)*1/90);
                set(LEFT_ROTATION,Location.getAngle(Inputs.getInputs().get(key),LEFT_BUTTON_CENTER));
                leftButtonSprite.setCenter(leftCurrentLocation.x, leftCurrentLocation.y);
                RenderStateManager.updatingState.updateElement(leftButtonId,RenderState.DEPTH_GAME_SCREEN,leftButtonSprite);
                leftActive = true;

                return;
            }


        }
        set(LEFT_ANALOG,0);
        set(LEFT_ROTATION,0);
        leftActive = false;
        leftCurrentLocation.x = LEFT_BUTTON_CENTER.x;
        leftCurrentLocation.y = LEFT_BUTTON_CENTER.y;
        leftButtonSprite.setCenter(leftCurrentLocation.x, leftCurrentLocation.y);
        RenderStateManager.updatingState.updateElement(leftButtonId,RenderState.DEPTH_GAME_SCREEN,leftButtonSprite);

    }

    private void updateRight(){
        for (Long key : Inputs.getInputs().keySet()){
            if(Location.getDistance(Inputs.getInputs().get(key), RIGHT_BUTTON_CENTER)<= RADIUS){
                rightCurrentLocation.x = Inputs.getInputs().get(key).x;
                rightCurrentLocation.y = Inputs.getInputs().get(key).y;
                set(RIGHT_ANALOG,Location.getDistance(Inputs.getInputs().get(key).x,Inputs.getInputs().get(key).y, RIGHT_BUTTON_CENTER.x,RIGHT_BUTTON_CENTER.y)*1/90);
                set(RIGHT_ROTATION,Location.getAngle(Inputs.getInputs().get(key),RIGHT_BUTTON_CENTER));
                rightButtonSprite.setCenter(rightCurrentLocation.x, rightCurrentLocation.y);
                RenderStateManager.updatingState.updateElement(rightButtonId,RenderState.DEPTH_GAME_SCREEN,rightButtonSprite);
                rightActive = true;
                return;
            }

        }
        set(RIGHT_ANALOG,0);
        set(RIGHT_ROTATION,0);
        rightActive = false;
        rightCurrentLocation.x = RIGHT_BUTTON_CENTER.x;
        rightCurrentLocation.y = RIGHT_BUTTON_CENTER.y;
        rightButtonSprite.setCenter(rightCurrentLocation.x, rightCurrentLocation.y);
        RenderStateManager.updatingState.updateElement(rightButtonId,RenderState.DEPTH_GAME_SCREEN,rightButtonSprite);

    }

}
