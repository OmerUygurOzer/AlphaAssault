package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.handlers.controls.InputReceiver;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.handlers.controls.Inputs;
import com.boomer.alphaassault.resources.Resource;
import org.omg.CORBA.portable.IDLEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 12/11/2015.
 */
public class Console extends Controller implements Renderable,InputReceiver {


    public static final int CONSOLE_BUTTON_WIDTH  = 180;
    public static final int CONSOLE_BUTTON_HEIGHT = 80;

    public static final int CONSOLE_BUTTON_X = GameGraphics.VIRTUAL_WIDTH - 100;
    public static final int CONSOLE_BUTTON_Y = 50;

    //INPUT MAPPING
    public static final int BUTTON_ONE_STATE = 0;
    public static final int BUTTON_TWO_STATE = 1;
    public static final int BUTTON_THREE_STATE = 2;
    public static final int BUTTON_FOUR_STATE = 3;

    //BUTTON STATES
    public static final int IDLE    = 0;
    public static final int PRESSED = 1;

    private int buttonNumber;

    public static final int BUTTON_ONE     = 0;
    public static final int BUTTON_TWO     = 1;
    public static final int BUTTON_THREE   = 2;
    public static final int BUTTON_FOUR    = 3;

    private Map<Integer,Button> buttons;
    private Map<Integer,Integer> localStates;

    //REFERENCE IDS
    private long referenceId;
    private long buttonOneId;
    private long buttonTwoId;
    private long buttonThreeId;
    private long buttonFourId;

    private int viewType;

    public Console() {
        super();
        referenceId = System.currentTimeMillis() + 100;
        buttonOneId = referenceId   + 0;
        buttonTwoId = referenceId   + 1;
        buttonThreeId = referenceId + 2;
        buttonFourId = referenceId  + 3 ;


        buttons     = new HashMap<Integer,Button>();
        localStates = new HashMap<Integer, Integer>();
        buttonNumber = 0;
    }

    public void addButton(int _key,Texture _texture){
        //CREATE BUTTON
        TextureRegion[][] buttonStates = TextureRegion.split(_texture,287,144);
        Button button = new Button(CONSOLE_BUTTON_X,CONSOLE_BUTTON_Y + buttonNumber*80,CONSOLE_BUTTON_WIDTH,CONSOLE_BUTTON_HEIGHT);
        button.addState(IDLE,buttonStates[0][IDLE]);
        button.addState(PRESSED,buttonStates[0][PRESSED]);

        //SET BUTTON PROPERTIES
        button.setReferenceID(referenceId + buttonNumber);
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
                Button button = buttons.get(index);
                boolean fitsX = Math.abs(button.getCenterX() - inputX)<button.getWidth()/2;
                boolean fitsY = Math.abs(button.getCenterY() - inputY)<button.getHeight()/2;
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
        for(Button button: buttons.values()){
            button.addToRenderState();
        }
    }

    @Override
    public void removeFromRenderState() {

    }

    @Override
    public long getReferenceID() {
        return referenceId;
    }

    @Override
    public void setReferenceID(long _referenceId) {

    }

    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }

}
