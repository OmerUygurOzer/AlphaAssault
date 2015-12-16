package com.boomer.alphaassault.graphics.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Rotation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 12/8/2015.
 */
public class BAnimation implements BDrawable {
    private Sprite currentSprite;
    private TextureRegion[][] textureRegions;

    private Map<Rotation,Sprite[]> spriteSheet;

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
        spriteSheet = new HashMap<Rotation, Sprite[]>();
        currentFrame = 0;
        timer = 0f;

        textureRegions = _textureRegions;
        generate();

    }


    private void generate(){
        frames = textureRegions[0].length;
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
        spriteSheet.put(Rotation.RIGHT,spritesRight);
        spriteSheet.put(Rotation.LEFT,spritesLeft);
        spriteSheet.put(Rotation.UP,spritesUp);
        spriteSheet.put(Rotation.DOWN,spritesDown);
        spriteSheet.put(Rotation.UP_RIGHT,spritesUpRight);
        spriteSheet.put(Rotation.UP_LEFT,spritesUpLeft);
        spriteSheet.put(Rotation.DOWN_RIGHT,spritesDownRight);
        spriteSheet.put(Rotation.DOWN_LEFT,spritesDownLeft);
        position = new Location();
        center = new Location();
    }

    public void setSecondsPerFrame(float _spf){
        SPF = _spf;
    }

    public void setSize(float _width,float _height){
        width = _width;
        height = _height;
        for(Rotation rotation : spriteSheet.keySet()){
            for(int i= 0 ;i < spriteSheet.get(Rotation.LEFT).length;i++){
                spriteSheet.get(rotation)[i].setSize(width,height);

            }
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
        timer += _deltaTime;
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

        currentSprite = spriteSheet.get(rotation)[currentFrame];
        currentSprite.setCenter(center.x,center.y);
        currentSprite.draw(_spriteBatch);

    }

    @Override
    public BDrawable copy() {
        BAnimation bAnimation = new BAnimation(textureRegions);
        bAnimation.set(this);
        return bAnimation;
    }

    @Override
    public Location getCenter() {
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

    public Location getPosition() {
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
}
