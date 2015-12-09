package com.boomer.alphaassault.graphics.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Omer on 12/9/2015.
 */
public interface BDrawable {
    public  void set(BDrawable _bDrawable);
    public  void draw(SpriteBatch _spriteBatch);
    public BDrawable copy();
}