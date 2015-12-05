package com.boomer.alphaassault.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.*;

/**
 * Created by Omer on 11/26/2015.
 */
public class GameGraphics {
    public static final int VIRTUAL_WIDTH = 800;
    public static final int VIRTUAL_HEIGHT = 400;

    public static final double VIRTUAL_ASPECT_RATIO = VIRTUAL_HEIGHT / VIRTUAL_WIDTH;
    public static double REAL_ASPECT_RATIO = Gdx.graphics.getHeight() / Gdx.graphics.getWidth();


}
