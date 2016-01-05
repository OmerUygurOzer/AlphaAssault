package core.inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import core.System;


/**
 * Created by Omer on 11/28/2015.
 */
public class InputManager {

    private int inputTouchLimit = 1;
    private int inputKeyLimit = 5;
    private int screenWidth;
    private int screenHeight;
    private Touch [] previousStateTouches;
    private Touch [] currentStateTouches;
    private Key [] previousStateKeys;
    private Key [] currentStateKeys;
    private Vector2 screenStartPoint = new Vector2(0,0);

    private class Touch{
        public int x;
        public int y;
        public boolean down;
        public Touch(){
            x =0;
            y =0;
            down = false;
        }
    }

    private class Key{
        int key;
        public boolean pressed;
        public Key(){
            key = 0;
            pressed = false;
        }
    }

    public InputManager(){
        previousStateKeys = new Key[inputKeyLimit];
        currentStateKeys = new Key[inputKeyLimit];

        for(int keyNumber = 0; keyNumber < inputKeyLimit; keyNumber++){
            previousStateKeys[keyNumber] = new Key();
            currentStateKeys[keyNumber] = new Key();
        }
    }

    public void setLimit(int _limit){
        inputTouchLimit = _limit;
        previousStateTouches = new Touch[inputTouchLimit];
        currentStateTouches = new Touch[inputTouchLimit];


        for(int touchNumber = 0; touchNumber< inputTouchLimit; touchNumber++){
            previousStateTouches[touchNumber] = new Touch();
            currentStateTouches[touchNumber] = new Touch();
        }


    }

    public void setScreenBounds(){
        int centerX = Gdx.graphics.getWidth() / 2;
        int centerY = Gdx.graphics.getHeight() / 2;
        double realAspectRatio = (double)Gdx.graphics.getWidth() / (double)Gdx.graphics.getHeight();
        if(realAspectRatio>= System.VIRTUAL_ASPECT_RATIO){ //LIMITING FACTOR = HEIGHT
                screenHeight = Gdx.graphics.getHeight();
                screenWidth = Math.round(((float)Gdx.graphics.getHeight()/(float)System.VIRTUAL_HEIGHT)*(float)System.VIRTUAL_WIDTH);
        }else{  //LIMITING FACTOR = WIDTH
                screenHeight = Math.round(((float)Gdx.graphics.getWidth()/(float)System.VIRTUAL_WIDTH) * (float) System.VIRTUAL_HEIGHT);
                screenWidth = Gdx.graphics.getWidth();
        }
        screenStartPoint.x = Math.round(centerX - screenWidth/2);
        screenStartPoint.y = Math.round(centerY - screenHeight/2);
    }


    public void poll(){

            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                currentStateKeys[0].pressed = true;
                currentStateKeys[0].key = Input.Keys.A;
            }else{
                currentStateKeys[0].pressed = false;
                currentStateKeys[0].key = Input.Keys.A;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                currentStateKeys[1].pressed = true;
                currentStateKeys[1].key = Input.Keys.S;
            }else{
                currentStateKeys[1].pressed = false;
                currentStateKeys[1].key = Input.Keys.S;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                currentStateKeys[2].pressed = true;
                currentStateKeys[2].key = Input.Keys.D;
            }else{
                currentStateKeys[2].pressed = false;
                currentStateKeys[2].key = Input.Keys.D;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                currentStateKeys[3].pressed = true;
                currentStateKeys[3].key = Input.Keys.W;
            }else{
                currentStateKeys[3].pressed = false;
                currentStateKeys[3].key = Input.Keys.W;
            }

            if(Gdx.input.isKeyPressed(Input.Keys.R)){
                currentStateKeys[4].pressed = true;
                currentStateKeys[4].key = Input.Keys.R;
             }else{
                currentStateKeys[4].pressed = false;
                currentStateKeys[4].key = Input.Keys.R;
            }

        for(int keyNumber = 0; keyNumber < inputKeyLimit; keyNumber++){
            if(!previousStateKeys[keyNumber].pressed && currentStateKeys[keyNumber].pressed){
                Inputs.inputAcquire(currentStateKeys[keyNumber].key);
            }

            if(previousStateKeys[keyNumber].pressed && currentStateKeys[keyNumber].pressed){
                Inputs.inputAcquire(currentStateKeys[keyNumber].key);
            }

            if(previousStateKeys[keyNumber].pressed && !currentStateKeys[keyNumber].pressed){
                Inputs.inputRelease(currentStateKeys[keyNumber].key);
            }

        }

        for(int keyNumber = 0; keyNumber < inputKeyLimit; keyNumber++){
            previousStateKeys[keyNumber].pressed = currentStateKeys[keyNumber].pressed;
            previousStateKeys[keyNumber].key = currentStateKeys[keyNumber].key;

        }


        for(int touchNumber = 0; touchNumber< inputTouchLimit; touchNumber++){
            if(Gdx.input.isTouched(touchNumber)) {
                currentStateTouches[touchNumber].x = Gdx.input.getX(touchNumber);
                currentStateTouches[touchNumber].y = Gdx.input.getY(touchNumber);
                currentStateTouches[touchNumber].down = true;
            }else{
                currentStateTouches[touchNumber].down = false;
            }
        }

        for(int touchNumber = 0; touchNumber< inputTouchLimit; touchNumber++){
            if((!previousStateTouches[touchNumber].down) && currentStateTouches[touchNumber].down){ //TOUCH DOWN
                touchDown(currentStateTouches[touchNumber].x, currentStateTouches[touchNumber].y);
            }
            if((previousStateTouches[touchNumber].down) && currentStateTouches[touchNumber].down){ //KEEP PRESSING
                touchDown(currentStateTouches[touchNumber].x, currentStateTouches[touchNumber].y);
            }
            if((previousStateTouches[touchNumber].down) && !currentStateTouches[touchNumber].down){ //TOUCH UP
                touchUp(currentStateTouches[touchNumber].x, currentStateTouches[touchNumber].y);
            }

        }

        for(int touchNumber = 0; touchNumber< inputTouchLimit; touchNumber++){
            previousStateTouches[touchNumber].x = currentStateTouches[touchNumber].x;
            previousStateTouches[touchNumber].y = currentStateTouches[touchNumber].y;
            previousStateTouches[touchNumber].down = currentStateTouches[touchNumber].down;
        }

        hover(Gdx.input.getX(),Gdx.input.getY());

    }

    private void touchDown(int _x,int _y){
        _y = Gdx.graphics.getHeight() - _y;
        int virtualX=0;
        int virtualY=0;
        boolean fitsX = _x <= screenStartPoint.x + screenWidth && _x > screenStartPoint.x;
        boolean fitsY  = _y <= screenStartPoint.y + screenHeight && _y >screenStartPoint.y;
        if(fitsX && fitsY){
            virtualX   = Math.round((float)(_x - screenStartPoint.x)/(float)screenWidth*(float)System.VIRTUAL_WIDTH);
            virtualY = Math.round((float)(_y - screenStartPoint.y)/(float)screenHeight*(float)System.VIRTUAL_HEIGHT);

        }
            Inputs.inputAcquire(virtualX, virtualY);
    }

    private void touchUp(int _x, int _y){
        _y = Gdx.graphics.getHeight() - _y;
        int virtualX=0;
        int virtualY=0;
        boolean fitsX = _x <= screenStartPoint.x + screenWidth && _x > screenStartPoint.x;
        boolean fitsY  = _y <= screenStartPoint.y + screenHeight && _y >screenStartPoint.y;
        if(fitsX && fitsY){
            virtualX   = Math.round((float)(_x - screenStartPoint.x)/(float)screenWidth*(float)System.VIRTUAL_WIDTH);
            virtualY = Math.round((float)(_y - screenStartPoint.y)/(float)screenHeight*(float)System.VIRTUAL_HEIGHT);
        }
            Inputs.inputRelease(virtualX, virtualY);
    }

    private void keyDown(int _key){
        Inputs.inputAcquire(_key);
    }
    private void keyUp(int _key){
        Inputs.inputRelease(_key);
    }

    private void hover(int _x, int _y){
        _y = Gdx.graphics.getHeight() - _y;
        int virtualX=0;
        int virtualY=0;
        boolean fitsX = _x <= screenStartPoint.x + screenWidth && _x > screenStartPoint.x;
        boolean fitsY  = _y <= screenStartPoint.y + screenHeight && _y >screenStartPoint.y;
        if(fitsX && fitsY){
            virtualX   = Math.round((float)(_x - screenStartPoint.x)/(float)screenWidth*(float)System.VIRTUAL_WIDTH);
            virtualY = Math.round((float)(_y - screenStartPoint.y)/(float)screenHeight*(float)System.VIRTUAL_HEIGHT);
        }

        Inputs.updateHover(virtualX,virtualY);

    }





}
