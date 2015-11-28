package com.boomer.alphaassault.graphics.GUI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public class GamePad implements Renderable {


    private Location GAME_FRAME_CENTER;

    private Sprite LEFT_BUTTON_SPRITE;
    private Sprite LEFT_CIRCLE_SPRITE;
    private Sprite GAME_FRAME;

    private static final int LEFT_BUTTON_SIZE = 40;
    private static final int LEFT_CIRCLE_SIZE = 180;

    private static final Location LEFT_BUTTON_CENTER = new Location(100,100);

    private long REFERENCE_ID;


    public GamePad() {
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
        addToRenderState();

    }

    @Override
    public void addToRenderState() {
        RenderStateManager.add(GameGraphics.CAMERA_TYPE_SCREEN,REFERENCE_ID,LEFT_BUTTON_SPRITE, LEFT_BUTTON_CENTER);
        RenderStateManager.add(GameGraphics.CAMERA_TYPE_SCREEN,REFERENCE_ID+1, LEFT_CIRCLE_SPRITE, LEFT_BUTTON_CENTER);
        RenderStateManager.add(GameGraphics.CAMERA_TYPE_SCREEN,REFERENCE_ID+2, GAME_FRAME, GAME_FRAME_CENTER);
    }

    @Override
    public void createReferenceID() {
        REFERENCE_ID = System.currentTimeMillis();
    }

    @Override
    public long getReferenceID() {
        return REFERENCE_ID;
    }


    public void updateButton(){

    }



}
