package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
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

    Sprite leftButtonSprite;
    Sprite leftCircleSprite;



    public Controller() {
        leftbuttonCenter = new Location(0,0);
        leftButtonSprite = new Sprite (Resource.getTexture(Resource.TEXTURE_LEFT_BUTTON));
        leftButtonSprite.setSize(10,10);
        leftButtonSprite.setCenter(leftbuttonCenter.x,leftbuttonCenter.y);
        leftCircleSprite = new Sprite(Resource.getTexture(Resource.TEXTURE_LEFT_CIRCLE));
        leftCircleSprite.setSize(80,80);
        leftCircleSprite.setCenter(leftbuttonCenter.x,leftbuttonCenter.y);

    }






    @Override
    public void render(SpriteBatch spriteBatch) {
        leftButtonSprite.draw(spriteBatch);
        leftCircleSprite.draw(spriteBatch);
    }
}
