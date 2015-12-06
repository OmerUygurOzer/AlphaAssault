package com.boomer.alphaassault.handlers.controls;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.utilities.Location;


/**
 * Created by Omer on 11/28/2015.
 */
public class InputManager{

    private int inputLimit =1;
    private int screenWidth;
    private int screenHeight;
    private Touch [] previousState;
    private Touch [] currentState;
    private Location screenStartPoint = new Location(0,0);

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


    public void setLimit(int _limit){
        inputLimit = _limit;
        previousState = new Touch[inputLimit];
        currentState = new Touch[inputLimit];

        for(int touchNumber = 0; touchNumber< inputLimit; touchNumber++){
            previousState[touchNumber] = new Touch();
            currentState[touchNumber] = new Touch();
        }

    }

    public void setScreenBounds(){
        int centerX = Gdx.graphics.getWidth() / 2;
        int centerY = Gdx.graphics.getHeight() / 2;
        double realAspectRatio = (double)Gdx.graphics.getWidth() / (double)Gdx.graphics.getHeight();
        if(realAspectRatio>= GameGraphics.VIRTUAL_ASPECT_RATIO){ //LIMITING FACTOR = HEIGHT
                screenHeight = Gdx.graphics.getHeight();
                screenWidth = Math.round(((float)Gdx.graphics.getHeight()/(float)GameGraphics.VIRTUAL_HEIGHT)*(float)GameGraphics.VIRTUAL_WIDTH);
        }else{  //LIMITING FACTOR = WIDTH
                screenHeight = Math.round(((float)Gdx.graphics.getWidth()/(float)GameGraphics.VIRTUAL_WIDTH) * (float) GameGraphics.VIRTUAL_HEIGHT);
                screenWidth = Gdx.graphics.getWidth();
        }
        screenStartPoint.x = Math.round(centerX - screenWidth/2);
        screenStartPoint.y = Math.round(centerY - screenHeight/2);
    }


    public void poll(){
        //setScreenBounds();
        for(int touchNumber = 0; touchNumber< inputLimit; touchNumber++){
            if(Gdx.input.isTouched(touchNumber)) {
                currentState[touchNumber].x = Gdx.input.getX(touchNumber);
                currentState[touchNumber].y = Gdx.input.getY(touchNumber);
                currentState[touchNumber].down = true;
            }else{
                currentState[touchNumber].down = false;
            }
        }

        for(int touchNumber = 0; touchNumber< inputLimit; touchNumber++){
            if((!previousState[touchNumber].down) && currentState[touchNumber].down){ //TOUCH DOWN
                touchDown(currentState[touchNumber].x,currentState[touchNumber].y);
            }
            if((previousState[touchNumber].down) && currentState[touchNumber].down){ //KEEP PRESSING
                touchDown(currentState[touchNumber].x,currentState[touchNumber].y);
            }
            if((previousState[touchNumber].down) && !currentState[touchNumber].down){ //TOUCH UP
                touchUp(currentState[touchNumber].x,currentState[touchNumber].y);
            }

        }

        for(int touchNumber = 0; touchNumber< inputLimit; touchNumber++){
            previousState[touchNumber].x = currentState[touchNumber].x;
            previousState[touchNumber].y = currentState[touchNumber].y;
            previousState[touchNumber].down = currentState[touchNumber].down;
        }


    }

    private void touchDown(int _x,int _y){
        _y = Gdx.graphics.getHeight() - _y;
        int virtualX=0;
        int virtualY=0;
        boolean fitsX = _x <= screenStartPoint.x + screenWidth && _x > screenStartPoint.x;
        boolean fitsY  = _y <= screenStartPoint.y + screenHeight && _y >screenStartPoint.y;
        if(fitsX && fitsY){
            virtualX   = Math.round((float)(_x - screenStartPoint.x)/(float)screenWidth*(float)GameGraphics.VIRTUAL_WIDTH);
            virtualY = Math.round((float)(_y - screenStartPoint.y)/(float)screenHeight*(float)GameGraphics.VIRTUAL_HEIGHT);
            //System.out.println(virtualX+ "  X  "  +virtualY);

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
            virtualX   = Math.round((float)(_x - screenStartPoint.x)/(float)screenWidth*(float)GameGraphics.VIRTUAL_WIDTH);
            virtualY = Math.round((float)(_y - screenStartPoint.y)/(float)screenHeight*(float)GameGraphics.VIRTUAL_HEIGHT);
        }
            Inputs.inputRelease(virtualX, virtualY);
    }





}
