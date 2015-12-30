package core.graphics.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import core.System;

/**
 * Created by Omer on 12/28/2015.
 */
public class ComponentView extends ScalingViewport {
    private int x;
    private int y;
    private float viewWidth;
    private float viewHeight;

    public ComponentView(int _x, int _y, float _viewWidth, float _viewHeight, Camera camera) {
        super(Scaling.fit, _viewWidth, _viewHeight, camera);
        viewWidth = _viewWidth;
        viewHeight = _viewHeight;
        x = _x;
        y = _y;
    }

    public ComponentView(int _x, int _y, float _viewWidth, float _viewHeight) {
        super(Scaling.fit, _viewWidth, _viewHeight);
        x = _x;
        y = _y;
    }

    @Override
    public void update(int screenWidth, int screenHeight, boolean centerCamera) {
        float screenRatioX = (float)screenWidth / (float)System.VIRTUAL_WIDTH;
        float screenRatioY = (float)screenHeight / (float)System.VIRTUAL_HEIGHT;
        int viewX =  Math.round(screenRatioX * x);
        int viewY =  Math.round(screenRatioY * y);
        int viewPortWidth = Math.round(screenRatioX * viewWidth);
        int viewPortHeight = Math.round(screenRatioY * viewHeight);
        setScreenBounds(viewX,viewY,viewPortWidth,viewPortHeight);
        apply(centerCamera);
    }

    public float getViewHeight(){return viewHeight;}
    public float getViewWidth(){return viewWidth;}
    public int getY(){return y;}
    public int getX(){return x;}

}
