package com.boomer.alphaassault.handlers.controls;

import com.badlogic.gdx.Gdx;



/**
 * Created by Omer on 11/28/2015.
 */
public class InputManager{

    private int MAX_INPUTS_LIMIT=0;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private static int INPUT_SEPARATOR;
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

    public void setInputSeparator(int _inputSeparator){
        INPUT_SEPARATOR = _inputSeparator;
    }

    public void setLimit(int _limit){
        MAX_INPUTS_LIMIT = _limit;
        previousState = new Touch[MAX_INPUTS_LIMIT];
        currentState = new Touch[MAX_INPUTS_LIMIT];

        for(int touchNumber=0;touchNumber<MAX_INPUTS_LIMIT;touchNumber++){
            previousState[touchNumber] = new Touch();
            currentState[touchNumber] = new Touch();
        }

    }

    public void setScreenBounds(int _width,int _height){
        SCREEN_WIDTH = _width;
        SCREEN_HEIGHT = _height;
    }

    public void poll(){
        for(int touchNumber=0;touchNumber<MAX_INPUTS_LIMIT;touchNumber++){
            if(Gdx.input.isTouched(touchNumber)) {
                currentState[touchNumber].x = Gdx.input.getX(touchNumber);
                currentState[touchNumber].y = Gdx.input.getY(touchNumber);
                currentState[touchNumber].down = true;
            }else{
                currentState[touchNumber].down = false;
            }
        }

        for(int touchNumber=0;touchNumber<MAX_INPUTS_LIMIT;touchNumber++){
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

        for(int touchNumber=0;touchNumber<MAX_INPUTS_LIMIT;touchNumber++){
            previousState[touchNumber].x = currentState[touchNumber].x;
            previousState[touchNumber].y = currentState[touchNumber].y;
            previousState[touchNumber].down = currentState[touchNumber].down;
        }


    }

    private void touchDown(int _x,int _y){
            Inputs.inputAcquire(_x, SCREEN_HEIGHT- _y);
    }

    private void touchUp(int _x, int _y){
            Inputs.inputRelease(_x, SCREEN_HEIGHT- _y);
    }





}
