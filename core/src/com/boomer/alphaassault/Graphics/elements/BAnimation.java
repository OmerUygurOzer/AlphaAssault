package com.boomer.alphaassault.graphics.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Rotation;

/**
 * Created by Omer on 12/8/2015.
 */
public class BAnimation implements BDrawable {
    private Sprite currentSprite;
    private TextureRegion[][] textureRegions;

    private Sprite[] spritesRight;
    private Sprite[] spritesLeft;
    private Sprite[] spritesUp;
    private Sprite[] spritesDown;
    private Sprite[] spritesUpRight;
    private Sprite[] spritesUpLeft;
    private Sprite[] spritesDownRight;
    private Sprite[] spritesDownLeft;

    private int currentFrame;
    private int frames;
    private float SPF; //SECONDS PER FRAME

    private float width;
    private float height;
    private Location position;
    private Location center;
    private Rotation rotation;
    private double facingAngle;

    private float timer;

    public BAnimation(TextureRegion[][] _textureRegions) {
        currentFrame = 0;
        timer = 0f;

        textureRegions = _textureRegions;
        generate();

    }


    private void generate(){
        frames = textureRegions[0].length;
        spritesRight = new Sprite[frames];
        spritesLeft = new Sprite[frames];
        spritesUp = new Sprite[frames];
        spritesDown = new Sprite[frames];
        spritesUpRight = new Sprite[frames];
        spritesUpLeft = new Sprite[frames];
        spritesDownLeft = new Sprite[frames];
        spritesDownRight = new Sprite[frames];

        for(int i=0;i<textureRegions[6].length;i++){
            spritesRight[i] = new Sprite(textureRegions[6][i]);
            spritesLeft[i] = new Sprite(textureRegions[2][i]);
            spritesUp[i] = new Sprite(textureRegions[4][i]);
            spritesDown[i] = new Sprite(textureRegions[0][i]);
            spritesUpRight[i] = new Sprite(textureRegions[5][i]);
            spritesUpLeft[i] = new Sprite(textureRegions[3][i]);
            spritesDownRight[i] = new Sprite(textureRegions[7][i]);
            spritesDownLeft[i] = new Sprite(textureRegions[1][i]);
        }

        position = new Location();
        center = new Location();
    }

    public void setSecondsPerFrame(float _spf){
        SPF = _spf;
    }

    public void setSize(float _width,float _height){
        width = _width;
        height = _height;
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


    public Rotation getRotation() {
        return rotation;
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
        width = incomingAnimation.getWidth();
        height = incomingAnimation.getHeight();
        rotation = incomingAnimation.getRotation();


    }

    @Override
    public void draw(SpriteBatch _spriteBatch) {
            timer += Gdx.graphics.getDeltaTime();
            if(timer>SPF){currentFrame++;}
            if(currentFrame==frames){currentFrame = 0;}
            switch (rotation){
                    case RIGHT:
                        currentSprite = spritesRight[currentFrame];
                        break;
                    case LEFT:
                        currentSprite = spritesLeft[currentFrame];
                        break;
                    case UP:
                        currentSprite = spritesUp[currentFrame];
                        break;
                    case DOWN:
                        currentSprite = spritesDown[currentFrame];
                        break;
                    case UP_RIGHT:
                        currentSprite = spritesUpRight[currentFrame];
                        break;
                    case UP_LEFT:
                        currentSprite = spritesUpLeft[currentFrame];
                        break;
                    case DOWN_RIGHT:
                        currentSprite = spritesDownRight[currentFrame];
                        break;
                    case DOWN_LEFT:
                        currentSprite = spritesDownLeft[currentFrame];
                        break;
                    default:
                        //DO NOTHING
                        break;


            }
        currentSprite.setCenter(center.x,center.y);
        currentSprite.setSize(width,height);
        currentSprite.draw(_spriteBatch);
    }

    @Override
    public BDrawable copy() {
        BAnimation bAnimation = new BAnimation(textureRegions);
        bAnimation.set(this);
        return bAnimation;
    }


    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Location getCenter() {
        return center;
    }

    public double getFacingAngle() {
        return facingAngle;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public Location getPosition() {
        return position;
    }

    public float getSPF() {
        return SPF;
    }

    public TextureRegion[][] getTextureRegions() {
        return textureRegions;
    }
}
