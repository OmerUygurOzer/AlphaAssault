package com.boomer.alphaassault.graphics.views;



import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.boomer.alphaassault.graphics.GameGraphics;



/**
 * Created by Omer on 12/5/2015.
 */
public class GameViewport extends ScalingViewport {
    public GameViewport(float worldWidth, float worldHeight, Camera camera) {
        super(Scaling.fit, worldWidth, worldHeight, camera);
    }

    public GameViewport(float worldWidth, float worldHeight) {
        super(Scaling.fit, worldWidth, worldHeight);
    }

    @Override
    public void update(int screenWidth, int screenHeight, boolean centerCamera) {
        double realAspectRatio = (double)screenWidth / (double)screenHeight;
        if(realAspectRatio >= GameGraphics.VIRTUAL_ASPECT_RATIO){
            super.update(screenWidth, screenHeight, centerCamera);
        }else{
            double updatedHeight = ((double)screenWidth/(double)GameGraphics.VIRTUAL_WIDTH) * (double)GameGraphics.VIRTUAL_HEIGHT;
            Vector2 scaled = Scaling.fit.apply(getWorldWidth(), getWorldHeight(), screenWidth,Math.round(updatedHeight));
            int viewportWidth = Math.round(scaled.x);
            int viewportHeight = Math.round(scaled.y);
            setScreenBounds((screenWidth - viewportWidth) / 2, (screenHeight - viewportHeight) / 2, viewportWidth, viewportHeight);
            apply(centerCamera);
        }


    }
}
