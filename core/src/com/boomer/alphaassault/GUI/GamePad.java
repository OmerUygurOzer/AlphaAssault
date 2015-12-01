package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.graphics.GameGraphics;
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
    //LEFT : ONLY LEFT CONSOLE
    //RIGHT: ONLY RIGHT CONSOLE
    //BOTH: RIGHT AND LEFT CONSOLES
    private int TYPE;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
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
    private int cameraType;

    private boolean leftActive;
    private boolean rightActive;

    //INPUT MAPPING
    public static final int LEFT_ANGLE = 0;
    public static final int LEFT_SPEED = 1;
    public static final int RIGHT_ANGLE = 2;
    public static final int RUGHT_SPEED = 3;


    public GamePad(int _type) {
        super();
        referenceId = System.currentTimeMillis();
        leftButtonId = referenceId;
        leftCircleId = referenceId+1;
        rightButtonId = referenceId+2;
        rightCircleId = referenceId+3;
        hudId = referenceId +4;

        //LEFT
        leftButtonSprite = new Sprite (Resource.getTexture(Resource.TEXTURE_LEFT_BUTTON));
        leftButtonSprite.setSize(BUTTON_SIZE, BUTTON_SIZE);
        leftButtonSprite.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        leftCircleSprite = new Sprite(Resource.getTexture(Resource.TEXTURE_LEFT_CIRCLE));
        leftCircleSprite.setSize(CIRCLE_SIZE, CIRCLE_SIZE);
        leftCircleSprite.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        leftCurrentLocation = new Location(LEFT_BUTTON_CENTER);
        leftActive = false;

        //RIGHT
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
        if(TYPE == LEFT || TYPE == BOTH) {
            RenderStateManager.add(cameraType, leftButtonId, leftButtonSprite);
            RenderStateManager.add(cameraType, leftCircleId, leftCircleSprite);
        }
        if(TYPE == RIGHT || TYPE == BOTH) {
            RenderStateManager.add(cameraType, rightButtonId, rightButtonSprite);
            RenderStateManager.add(cameraType, rightCircleId, rightCircleSprite);
        }
        RenderStateManager.add(cameraType, hudId, gameFrame);
    }

    @Override
    public void createReferenceID() {
        referenceId = System.currentTimeMillis();
    }

    @Override
    public long getReferenceID() {
        return referenceId;
    }

    @Override
    public void setCameraType(int _cameraType) {
        cameraType = _cameraType;
    }


    @Override
    public void receiveInput() {
        if(TYPE == LEFT || TYPE == BOTH) {
            updateLeft();
        }
        if(TYPE == RIGHT || TYPE == BOTH) {
            updateRight();
        }
    }

    private void updateLeft(){
        for (Long key : Inputs.getInputs().keySet()){
            if(Location.getDistance(Inputs.getInputs().get(key), LEFT_BUTTON_CENTER)< RADIUS){
                leftCurrentLocation.x = Inputs.getInputs().get(key).x;
                leftCurrentLocation.y = Inputs.getInputs().get(key).y;
                leftButtonSprite.setCenter(leftCurrentLocation.x, leftCurrentLocation.y);
                RenderStateManager.updatingState.updateElement(leftButtonId,leftButtonSprite);
                leftActive = true;
                return;
            }


        }
        leftActive = false;
        leftCurrentLocation.x = LEFT_BUTTON_CENTER.x;
        leftCurrentLocation.y = LEFT_BUTTON_CENTER.y;
        leftButtonSprite.setCenter(leftCurrentLocation.x, leftCurrentLocation.y);
        RenderStateManager.updatingState.updateElement(leftButtonId,leftButtonSprite);

    }

    private void updateRight(){
        for (Long key : Inputs.getInputs().keySet()){
            if(Location.getDistance(Inputs.getInputs().get(key), RIGHT_BUTTON_CENTER)< RADIUS){
                rightCurrentLocation.x = Inputs.getInputs().get(key).x;
                rightCurrentLocation.y = Inputs.getInputs().get(key).y;
                rightButtonSprite.setCenter(rightCurrentLocation.x, rightCurrentLocation.y);
                RenderStateManager.updatingState.updateElement(rightButtonId,rightButtonSprite);
                rightActive = true;
                return;
            }

        }

        rightActive = false;
        rightCurrentLocation.x = RIGHT_BUTTON_CENTER.x;
        rightCurrentLocation.y = RIGHT_BUTTON_CENTER.y;
        rightButtonSprite.setCenter(rightCurrentLocation.x, rightCurrentLocation.y);
        RenderStateManager.updatingState.updateElement(rightButtonId,rightButtonSprite);

    }

}
