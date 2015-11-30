package com.boomer.alphaassault.graphics.GUI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.handlers.controls.Inputs;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.InputReceiver;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public class GamePad implements Renderable,InputReceiver {

    //GAMEPAD TYPE
    //LEFT : ONLY LEFT CONSOLE
    //RIGHT: ONLY RIGHT CONSOLE
    //BOTH: RIGHT AND LEFT CONSOLES
    private int TYPE;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int BOTH = 2;

    private Location gameFrameCenter;

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

    private long referenceId;
    private int cameraType;

    private boolean leftActive;
    private boolean rightActive;



    public GamePad(int _type) {
        referenceId = System.currentTimeMillis();

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
        gameFrameCenter = new Location(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);


        TYPE = _type;

    }

    @Override
    public void addToRenderState() {
        RenderStateManager.add(cameraType, referenceId, leftButtonSprite);
        RenderStateManager.add(cameraType, referenceId +1, leftCircleSprite);
        RenderStateManager.add(cameraType, referenceId +2, rightCircleSprite);
        RenderStateManager.add(cameraType, referenceId +3, rightButtonSprite);
        RenderStateManager.add(cameraType, referenceId +4, gameFrame);
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
        if(!Inputs.getInputs().isEmpty()){
            for (Long key : Inputs.getInputs().keySet()){
                if(Location.getDistance(Inputs.getInputs().get(key), leftCurrentLocation)< RADIUS){
                    leftCurrentLocation.x = Inputs.getInputs().get(key).x;
                    leftCurrentLocation.y = Inputs.getInputs().get(key).y;
                    leftButtonSprite.setCenter(leftCurrentLocation.x, leftCurrentLocation.y);
                    RenderStateManager.updatingState.updateElement(referenceId,leftButtonSprite);
                    leftActive = true;

                }
                if(Location.getDistance(Inputs.getInputs().get(key), rightCurrentLocation)< RADIUS){
                    rightCurrentLocation.x = Inputs.getInputs().get(key).x;
                    rightCurrentLocation.y = Inputs.getInputs().get(key).y;
                    rightButtonSprite.setCenter(rightCurrentLocation.x, rightCurrentLocation.y);
                    RenderStateManager.updatingState.updateElement(referenceId+3,leftButtonSprite);
                    rightActive = true;
                }

            }

        }



        leftCurrentLocation.x = LEFT_BUTTON_CENTER.x;
        leftCurrentLocation.y = LEFT_BUTTON_CENTER.y;
        leftButtonSprite.setCenter(leftCurrentLocation.x, leftCurrentLocation.y);
        RenderStateManager.updatingState.updateElement(referenceId,leftButtonSprite);
    }

    private void updateLeft(){

    }

    private void updateRight(){

    }
}
