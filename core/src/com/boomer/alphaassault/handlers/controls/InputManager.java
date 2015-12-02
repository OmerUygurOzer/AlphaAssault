package com.boomer.alphaassault.handlers.controls;

import com.badlogic.gdx.Gdx;



/**
 * Created by Omer on 11/28/2015.
 */
public class InputManager{

    private int inputLimit =1;
    private int screenWidth;
    private int screenHeight;
    private Touch [] previousState;
    private Touch [] currentState;

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

    public void setScreenBounds(int _width,int _height){
        screenWidth = _width;
        screenHeight = _height;
    }

    public void poll(){
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
            if((previousState[touchNumber].down) && currentState[touchNumber].down){ //DRAG
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
            int virtualX = _x* screenWidth /Gdx.graphics.getWidth();
            int virtualY = screenHeight - _y* screenHeight /Gdx.graphics.getHeight();
            Inputs.inputAcquire(virtualX, virtualY);
    }

    private void touchUp(int _x, int _y){
        int virtualX = _x* screenWidth /Gdx.graphics.getWidth();
        int virtualY = screenHeight - _y* screenHeight /Gdx.graphics.getHeight();
        Inputs.inputRelease(virtualX, virtualY);
    }





}
