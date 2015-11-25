package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public class Controller implements Renderable {

    public static final Texture leftButtonTexture;
    public static final Texture leftCircleTexture;

    Location leftbuttonCenter;

    Sprite leftButtonSprite;
    Sprite leftCircleSprite;

    static{
        leftButtonTexture = new Texture(Gdx.files.internal("greenbutton.png"));
        leftCircleTexture = new Texture(Gdx.files.internal("greencircle.png"));
    }

    public Controller() {
        leftbuttonCenter = new Location(0,0);
        leftButtonSprite = new Sprite (leftButtonTexture);
        leftButtonSprite.setSize(10,10);
        leftButtonSprite.setCenter(leftbuttonCenter.x,leftbuttonCenter.y);
        leftCircleSprite = new Sprite(leftCircleTexture);
        leftCircleSprite.setSize(80,80);
        leftCircleSprite.setCenter(leftbuttonCenter.x,leftbuttonCenter.y);

    }






    @Override
    public void render(SpriteBatch spriteBatch) {
        leftButtonSprite.draw(spriteBatch);
        leftCircleSprite.draw(spriteBatch);
    }
}
