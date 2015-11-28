package com.boomer.alphaassault.graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public class Controller implements Renderable {

    Location leftbuttonCenter;
    Location gameFrameCenter;

    Sprite leftButtonSprite;
    Sprite leftCircleSprite;
    Sprite gameFrame;



    public Controller() {
        leftbuttonCenter = new Location(0,0);
        leftButtonSprite = new Sprite (Resource.getTexture(Resource.TEXTURE_LEFT_BUTTON));
        leftButtonSprite.setSize(10,10);
        leftButtonSprite.setCenter(leftbuttonCenter.x,leftbuttonCenter.y);
        leftCircleSprite = new Sprite(Resource.getTexture(Resource.TEXTURE_LEFT_CIRCLE));
        leftCircleSprite.setSize(80,80);
        leftCircleSprite.setCenter(leftbuttonCenter.x,leftbuttonCenter.y);
        gameFrame = new Sprite(Resource.getTexture(Resource.TEXTURE_HUD_CAM));
        gameFrame.setSize(400,400);
        gameFrame.setCenter(400,200);

    }






    @Override
    public void render(SpriteBatch _spriteBatch) {
        leftButtonSprite.draw(_spriteBatch);
        leftCircleSprite.draw(_spriteBatch);
        gameFrame.draw(_spriteBatch);
    }
}
