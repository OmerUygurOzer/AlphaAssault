package com.boomer.alphaassault.graphics.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.settings.GameSettings;
import com.boomer.alphaassault.utilities.Rotation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 12/8/2015.
 */
public class BAnimation implements BDrawable {
    private Sprite currentSprite;
    private TextureRegion[][] textureRegions;
    private Type type;

    private Map<Rotation,Sprite[]> spriteSheetDirectional;
    private Sprite[] spriteSheetStill;

    private int currentFrame;
    private int frames;
    private float SPF; //SECONDS PER FRAME


    private float width;
    private float height;
    private Vector2 position;
    private Vector2 center;
    private Rotation rotation;
    private double facingAngle;

    private float timer;


    public BAnimation(TextureRegion[][] _textureRegions,Type _type) {
        type = _type;
        currentFrame = 0;
        timer = 0f;


        textureRegions = _textureRegions;
        switch (type){
            case DIRECTIONAL:
                spriteSheetDirectional = new HashMap<Rotation, Sprite[]>();
                break;
            case STILL:
                facingAngle = 0;
                break;
        }


        generate();

    }


    private void generate(){
        frames = textureRegions[0].length;
        switch (type){
            case DIRECTIONAL:

                Sprite [] spritesRight = new Sprite[frames];
                Sprite [] spritesLeft = new Sprite[frames];
                Sprite [] spritesUp = new Sprite[frames];
                Sprite [] spritesDown = new Sprite[frames];
                Sprite [] spritesUpRight = new Sprite[frames];
                Sprite [] spritesUpLeft = new Sprite[frames];
                Sprite [] spritesDownLeft = new Sprite[frames];
                Sprite [] spritesDownRight = new Sprite[frames];

                for(int i=0;i<textureRegions[0].length;i++){
                    spritesRight[i] =new Sprite(textureRegions[6][i]);
                    spritesLeft[i] = new Sprite(textureRegions[2][i]);
                    spritesUp[i] = new Sprite(textureRegions[4][i]);
                    spritesDown[i] = new Sprite(textureRegions[0][i]);
                    spritesUpRight[i] = new Sprite(textureRegions[5][i]);
                    spritesUpLeft[i] = new Sprite(textureRegions[3][i]);
                    spritesDownRight[i] = new Sprite(textureRegions[7][i]);
                    spritesDownLeft[i] = new Sprite(textureRegions[1][i]);


                }
                spriteSheetDirectional.put(Rotation.RIGHT,spritesRight);
                spriteSheetDirectional.put(Rotation.LEFT,spritesLeft);
                spriteSheetDirectional.put(Rotation.UP,spritesUp);
                spriteSheetDirectional.put(Rotation.DOWN,spritesDown);
                spriteSheetDirectional.put(Rotation.UP_RIGHT,spritesUpRight);
                spriteSheetDirectional.put(Rotation.UP_LEFT,spritesUpLeft);
                spriteSheetDirectional.put(Rotation.DOWN_RIGHT,spritesDownRight);
                spriteSheetDirectional.put(Rotation.DOWN_LEFT,spritesDownLeft);

                break;
            case STILL:
                spriteSheetStill = new Sprite[frames];

                for(int i=0;i<textureRegions[0].length;i++){
                   spriteSheetStill[i] = new Sprite(textureRegions[0][i]);
                }




                break;
            default:
                break;
        }


        position = new Vector2();
        center = new Vector2();
    }

    public void setSecondsPerFrame(float _spf){
        SPF = _spf;
    }

    public void setSize(float _width,float _height){
        width = _width;
        height = _height;
        switch (type){
            case DIRECTIONAL:
                for(Rotation rotation : spriteSheetDirectional.keySet()){
                    for(int i = 0; i < frames; i++){
                        spriteSheetDirectional.get(rotation)[i].setSize(width,height);

                    }
                }
                break;
            case STILL:
                for(int i = 0; i < frames; i++){
                    spriteSheetStill[i].setSize(width,height);

                }
                break;
        }

    }

    public void setFacingAngle(double _facingAngle){
        facingAngle = _facingAngle;
        rotation = Rotation.getRotation(_facingAngle);
    }

    public void setCenter(float _x,float _y){
        center.x = _x;
        center.y = _y;
        position.x = _x - width/2;
        position.y = _y + height/2;
    }

    public void setPosition(float _x,float _y){
        position.x = _x;
        position.y = _y;
        center.x = _x + width/2;
        center.y = _y - height/2;
    }

    public void update(float _deltaTime){
        timer += GameSettings.UPS;
        if(timer>SPF){currentFrame++;timer=0f;}
        if(currentFrame==frames){currentFrame = 0;}

    }


    @Override
    public void set(BDrawable _bDrawable) {
        BAnimation incomingAnimation = (BAnimation)_bDrawable;
        facingAngle = incomingAnimation.getFacingAngle();
        currentFrame = incomingAnimation.getCurrentFrame();
        position.x = incomingAnimation.getPosition().x;
        position.y = incomingAnimation.getPosition().y;
        center.x = incomingAnimation.getCenter().x;
        center.y = incomingAnimation.getCenter().y;
        rotation = Rotation.getRotation(facingAngle);
        SPF = incomingAnimation.getSPF();


        if(width != ((BAnimation) _bDrawable).getWidth() || height != ((BAnimation) _bDrawable).getHeight()){
            width = incomingAnimation.getWidth();
            height = incomingAnimation.getHeight();
            setSize(width,height);
        }

    }

    @Override
    public void draw(SpriteBatch _spriteBatch) {

        switch (type){
            case DIRECTIONAL:
                currentSprite = spriteSheetDirectional.get(rotation)[currentFrame];
                break;
            case STILL:
                currentSprite = spriteSheetStill[currentFrame];
                break;
        }

        currentSprite.setCenter(center.x,center.y);
        currentSprite.draw(_spriteBatch);

    }

    @Override
    public BDrawable copy() {
        BAnimation bAnimation = new BAnimation(textureRegions,type);
        bAnimation.set(this);
        return bAnimation;
    }

    @Override
    public Vector2 getCenter() {
        return center;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public double getFacingAngle() {
        return facingAngle;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getSPF() {
        return SPF;
    }

    public TextureRegion[][] getTextureRegions() {
        return textureRegions;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public enum Type{
        DIRECTIONAL,
        STILL;
    }
}
