package com.boomer.alphaassault.handlers.controls;

import com.boomer.alphaassault.graphics.GameGraphics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 12/24/2015.
 */
public class Console extends Controller {


    public static final int CONSOLE_BUTTON_WIDTH  = 180;
    public static final int CONSOLE_BUTTON_HEIGHT = 80;

    public static final int CONSOLE_BUTTON_X = GameGraphics.VIRTUAL_WIDTH - 100;
    public static final int CONSOLE_BUTTON_Y = 50;

    public static final int CONSOLE_BUTTON_ICON_WIDTH = 50;
    public static final int CONSOLE_BUTTON_ICON_HEIGHT = 50;


    //BUTTON STATES
    public static final int IDLE    = 0;
    public static final int PRESSED = 1;

    private int buttonNumber;



    private Map<Integer,Button> buttons;
    private Map<Integer,Integer> localStates;



    private int viewType;

    public Console() {
        super();


        buttons     = new HashMap<Integer,Button>();
        localStates = new HashMap<Integer, Integer>();
        buttonNumber = 0;

    }

    public void addButton(int _key){
        Button button = new Button(CONSOLE_BUTTON_X,CONSOLE_BUTTON_Y + buttonNumber*80,CONSOLE_BUTTON_WIDTH,CONSOLE_BUTTON_HEIGHT);
        localStates.put(_key,IDLE);
        buttons.put(_key,button);
        buttonNumber++;

    }

    @Override
    public void receiveInput() {
        for(Integer key: localStates.keySet()){
            localStates.put(key,IDLE);
        }

        for (Long key : Inputs.getInputs().keySet()){
            float inputX = Inputs.getInputs().get(key).x;
            float inputY = Inputs.getInputs().get(key).y;
            for(int index : buttons.keySet()) {
                Button button = buttons.get(index);
                boolean fitsX = Math.abs(button.getCenter().x - inputX)<button.getWidth()/2;
                boolean fitsY = Math.abs(button.getCenter().y - inputY)<button.getHeight()/2;
                if(fitsX && fitsY){
                    set(index,PRESSED);
                    localStates.put(index,PRESSED);

                }
            }

        }

        for(Integer key: localStates.keySet()){
            if(localStates.get(key)==IDLE){
                set(key,IDLE);
            }
        }


    }



}
