package com.boomer.alphaassault.graphics.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Omer on 12/9/2015.
 */
public interface BDrawable {
    void set(BDrawable _bDrawable);
    void draw(SpriteBatch _spriteBatch);
    BDrawable copy();
    Vector2 getCenter();
}
