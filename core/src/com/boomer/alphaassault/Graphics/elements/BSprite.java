package com.boomer.alphaassault.graphics.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Omer on 12/8/2015.
 */
public class BSprite implements BDrawable {

    private Sprite sprite;

    public BSprite(Sprite _sprite){
        sprite = new Sprite(_sprite);
    }

    public BSprite(TextureRegion _textureRegion){
        sprite = new Sprite(_textureRegion);
    }

    public BSprite(Texture _texture){
        sprite = new Sprite(_texture);
    }

    public BSprite(BDrawable _bDrawable) {
        sprite = new Sprite(((BSprite)_bDrawable).getSprite());
    }

    @Override
    public void set(BDrawable _bDrawable) {
        sprite.set(((BSprite)_bDrawable).getSprite());
    }

    @Override
    public void draw(SpriteBatch _spriteBatch) {
            sprite.draw(_spriteBatch);
    }

    @Override
    public BDrawable copy() {
        BSprite bSprite = new BSprite(sprite);
        return bSprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setCenter(float _x,float _y){
        sprite.setCenter(_x,_y);
    }

    public void setPosition(float _x,float _y){
        sprite.setPosition(_x,_y);
    }

    public void setSize(float _width,float _height){
        sprite.setSize(_width,_height);
    }
}
