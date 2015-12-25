package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gamestates.GameState;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.handlers.controls.Console;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.handlers.controls.InputReceiver;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.handlers.controls.Inputs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 12/11/2015.
 */
public class ConsoleWithGUI extends Console implements Renderable,InputReceiver {

    //BUTTON STATES
    public static final int IDLE    = 0;
    public static final int PRESSED = 1;

    private int buttonNumber;

    private Map<Integer,ButtonWithGUI> buttons;
    private Map<Integer,Integer> localStates;

    //REFERENCE IDS
    private short referenceId;
    private short buttonOneId;
    private short buttonTwoId;
    private short buttonThreeId;
    private short buttonFourId;

    private short[] buttonIds;

    private int viewType;

    public ConsoleWithGUI() {
        super();

        buttonIds = new short[4];
        for(int i= 0;i<4;i++){
            buttonIds[i]= GameSystem.obtainReference();
        }

        buttons     = new HashMap<Integer,ButtonWithGUI>();
        localStates = new HashMap<Integer, Integer>();
        buttonNumber = 0;

        referenceId = GameSystem.obtainReference();
    }

    public void addButton(int _key,TextureRegion[][] _states,TextureRegion _icon){
        //CREATE BUTTON
        TextureRegion[][] buttonStates = _states;
        ButtonWithGUI button = new ButtonWithGUI(CONSOLE_BUTTON_X,CONSOLE_BUTTON_Y + buttonNumber*80,CONSOLE_BUTTON_WIDTH,CONSOLE_BUTTON_HEIGHT);
        button.addState(IDLE,buttonStates[0][IDLE]);
        button.addState(PRESSED,buttonStates[0][PRESSED]);

        //SET BUTTON PROPERTIES
        button.setIcon(_icon, CONSOLE_BUTTON_ICON_WIDTH, CONSOLE_BUTTON_ICON_HEIGHT);
        button.setViewType(viewType);

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
                ButtonWithGUI button = buttons.get(index);
                boolean fitsX = Math.abs(button.getCenter().x - inputX)<button.getWidth()/2;
                boolean fitsY = Math.abs(button.getCenter().y - inputY)<button.getHeight()/2;
                if(fitsX && fitsY){
                    button.setState(PRESSED);
                    set(index,PRESSED);
                    localStates.put(index,PRESSED);

                }
            }

        }

        for(Integer key: localStates.keySet()){
            if(localStates.get(key)==IDLE){
                set(key,IDLE);
                buttons.get(key).resetState();
            }
        }


    }

    @Override
    public void addToRenderState() {
        for(ButtonWithGUI button: buttons.values()){
            button.addToRenderState();
        }
    }

    @Override
    public void removeFromRenderState() {

    }

    @Override
    public short getReferenceID() {
        return referenceId;
    }


    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }

}
