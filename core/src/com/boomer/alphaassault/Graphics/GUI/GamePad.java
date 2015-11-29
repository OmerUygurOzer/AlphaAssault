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

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int BOTH = 2;

    private Location GAME_FRAME_CENTER;

    private Sprite LEFT_BUTTON_SPRITE;
    private Sprite LEFT_CIRCLE_SPRITE;
    private Sprite GAME_FRAME;

    private static final int LEFT_BUTTON_SIZE = 40;
    private static final int LEFT_CIRCLE_SIZE = 180;
    private static final int LEFT_RADIUS = 90;

    private static final Location LEFT_BUTTON_CENTER = new Location(100,100);
    private Location CURRENT_LOCATION;

    private long REFERENCE_ID;
    private int CAMERA_TYPE;

    private int TYPE;



    public GamePad(int _type) {
        REFERENCE_ID = System.currentTimeMillis();
        LEFT_BUTTON_SPRITE = new Sprite (Resource.getTexture(Resource.TEXTURE_LEFT_BUTTON));
        LEFT_BUTTON_SPRITE.setSize(LEFT_BUTTON_SIZE,LEFT_BUTTON_SIZE);
        LEFT_BUTTON_SPRITE.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        LEFT_CIRCLE_SPRITE = new Sprite(Resource.getTexture(Resource.TEXTURE_LEFT_CIRCLE));
        LEFT_CIRCLE_SPRITE.setSize(LEFT_CIRCLE_SIZE,LEFT_CIRCLE_SIZE);
        LEFT_CIRCLE_SPRITE.setCenter(LEFT_BUTTON_CENTER.x, LEFT_BUTTON_CENTER.y);
        GAME_FRAME = new Sprite(Resource.getTexture(Resource.TEXTURE_HUD_CAM));
        GAME_FRAME.setSize(GameGraphics.VIRTUAL_HEIGHT,GameGraphics.VIRTUAL_HEIGHT);
        GAME_FRAME.setCenter(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);
        GAME_FRAME_CENTER = new Location(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);

        CURRENT_LOCATION = new Location(LEFT_BUTTON_CENTER);
        TYPE = _type;

    }

    @Override
    public void addToRenderState() {
        RenderStateManager.add(CAMERA_TYPE,REFERENCE_ID,LEFT_BUTTON_SPRITE, LEFT_BUTTON_CENTER);
        RenderStateManager.add(CAMERA_TYPE,REFERENCE_ID+1, LEFT_CIRCLE_SPRITE, LEFT_BUTTON_CENTER);
        RenderStateManager.add(CAMERA_TYPE,REFERENCE_ID+2, GAME_FRAME, GAME_FRAME_CENTER);
    }

    @Override
    public void createReferenceID() {
        REFERENCE_ID = System.currentTimeMillis();
    }

    @Override
    public long getReferenceID() {
        return REFERENCE_ID;
    }

    @Override
    public void setCameraType(int _cameraType) {
        CAMERA_TYPE = _cameraType;
    }


    @Override
    public void receiveInput() {
        if(!Inputs.getInputs().isEmpty()){
            for (Long key : Inputs.getInputs().keySet()){
                if(Location.getDistance(Inputs.getInputs().get(key),CURRENT_LOCATION)<LEFT_RADIUS){
                    CURRENT_LOCATION.x = Inputs.getInputs().get(key).x;
                    CURRENT_LOCATION.y = Inputs.getInputs().get(key).y;
                    LEFT_BUTTON_SPRITE.setCenter(CURRENT_LOCATION.x,CURRENT_LOCATION.y);
                    return;
                }
            }

        }


        CURRENT_LOCATION.x = 100f;
        CURRENT_LOCATION.y = 100f;
        LEFT_BUTTON_SPRITE.setCenter(CURRENT_LOCATION.x,CURRENT_LOCATION.y);
    }
}
