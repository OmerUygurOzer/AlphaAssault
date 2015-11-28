package com.boomer.alphaassault.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.*;

/**
 * Created by Omer on 11/26/2015.
 */
public class GameGraphics {
    public static final int VIRTUAL_WIDTH = 800;
    public static final int VIRTUAL_HEIGHT = 400;
    public static final int ASPECT_RATIO = VIRTUAL_HEIGHT / VIRTUAL_WIDTH;

    public static final OrthographicCamera SCREEN_CAM;
    public static final OrthographicCamera MAP_CAM;

    public static final int CAMERA_TYPE_SCREEN = 0;
    public static final int CAMERA_TYPE_MAP = 1;

    public static SpriteBatch MAIN_SPRITE_BATCH;

    public static Viewport SCREEN_VIEW;
    public static Viewport MAP_VIEW;

    static{
        MAIN_SPRITE_BATCH = new SpriteBatch();
        SCREEN_CAM = new OrthographicCamera();
        SCREEN_VIEW = new FitViewport(VIRTUAL_WIDTH,VIRTUAL_HEIGHT,SCREEN_CAM);
        SCREEN_CAM.translate(VIRTUAL_WIDTH/2,VIRTUAL_HEIGHT/2);
        SCREEN_CAM.update();

        MAP_CAM = new OrthographicCamera();
        MAP_VIEW = new FitViewport(VIRTUAL_HEIGHT,VIRTUAL_HEIGHT,MAP_CAM);
        MAP_CAM.translate(VIRTUAL_HEIGHT/2,VIRTUAL_HEIGHT/2);
        MAP_CAM.update();

    }
}
