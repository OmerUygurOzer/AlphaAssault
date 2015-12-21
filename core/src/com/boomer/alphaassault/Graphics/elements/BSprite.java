package com.boomer.alphaassault.graphics.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Omer on 12/8/2015.
 */
public class BSprite implements BDrawable {

    private Sprite sprite;
    private Vector2 center;

    public BSprite(Sprite _sprite){
        sprite = new Sprite(_sprite);
        center = new Vector2();
    }

    public BSprite(TextureRegion _textureRegion){
        sprite = new Sprite(_textureRegion);
        center = new Vector2();
    }

    public BSprite(Texture _texture){
        sprite = new Sprite(_texture);
        center = new Vector2();
    }

    public BSprite(BDrawable _bDrawable) {
        sprite = new Sprite(((BSprite)_bDrawable).getSprite());
        center = new Vector2();
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
        bSprite.setCenter(center.x,center.y);
        return bSprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setCenter(float _x,float _y){
        sprite.setCenter(_x,_y);
        center.x = _x;
        center.y = _y;
    }

    public void setPosition(float _x,float _y){
        sprite.setPosition(_x,_y);
        center.x = _x + sprite.getWidth()/2;
        center.y = _y + sprite.getHeight()/2;
    }

    public void setSize(float _width,float _height){
        sprite.setSize(_width,_height);
    }

    public Vector2 getCenter(){return center;}

    public void rotate(double _angle){
        sprite.rotate((float)_angle);
    }
}

