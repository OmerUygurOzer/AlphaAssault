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
import java.util.List;

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

    private List<Button> buttons;

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
        buttonFourId = referenceId  + 3;


        buttons = new ArrayList<Button>();
        buttonNumber = 0;

        addButton(Resource.getTexture(Resource.TEXTURE_BUTTON_BASE));
        addButton(Resource.getTexture(Resource.TEXTURE_BUTTON_BASE));
        addButton(Resource.getTexture(Resource.TEXTURE_BUTTON_BASE));
        addToRenderState();
    }

    public void addButton(Texture _texture){
        //CREATE BUTTON
        TextureRegion[][] buttonStates = TextureRegion.split(_texture,287,144);
        Button button = new Button(CONSOLE_BUTTON_X,CONSOLE_BUTTON_Y + buttonNumber*80,CONSOLE_BUTTON_WIDTH,CONSOLE_BUTTON_HEIGHT);
        button.addState(IDLE,buttonStates[0][IDLE]);
        button.addState(PRESSED,buttonStates[0][PRESSED]);

        //SET BUTTON PROPERTIES
        button.setReferenceID(referenceId + buttonNumber + 0);
        button.setViewType(viewType);


        buttons.add(button);

        buttonNumber++;

    }

    @Override
    public void receiveInput() {
        int[] buttonStates = new int[buttons.size()];
        for(int index = 0;index < buttons.size();index++){
            buttonStates[index] = IDLE;
        }

        for (Long key : Inputs.getInputs().keySet()){
            float inputX = Inputs.getInputs().get(key).x;
            float inputY = Inputs.getInputs().get(key).y;
            for(int index=0;index<buttons.size();index++) {
                Button button = buttons.get(index);
                boolean fitsX = Math.abs(button.getCenterX() - inputX)<button.getWidth()/2;
                boolean fitsY = Math.abs(button.getCenterY() - inputY)<button.getHeight()/2;
                if(fitsX && fitsY){
                    button.setState(PRESSED);
                        switch (index){
                            case BUTTON_ONE:
                                buttonStates[BUTTON_ONE] = PRESSED;
                                set(BUTTON_ONE_STATE,PRESSED);
                               // System.out.println("1");
                                break;
                            case BUTTON_TWO:
                                buttonStates[BUTTON_TWO] = PRESSED;
                                set(BUTTON_TWO_STATE,PRESSED);
                               // System.out.println("2");
                                break;
                            case BUTTON_THREE:
                                buttonStates[BUTTON_THREE] = PRESSED;
                                set(BUTTON_THREE_STATE,PRESSED);
                               // System.out.println("3");
                                break;
                            case BUTTON_FOUR:
                                buttonStates[BUTTON_FOUR] = PRESSED;
                                set(BUTTON_FOUR_STATE,PRESSED);
                               // System.out.println("4");
                                break;
                            default:
                                //DO NOTHING
                                break;
                        }
                }
            }

        }

        for(int index = 0;index < buttons.size();index++){
           if( buttonStates[index] == IDLE){
                set(index,IDLE);
               buttons.get(index).resetState();
           }
        }

    }

    @Override
    public void addToRenderState() {
        for(Button button: buttons){
            button.addToRenderState();
        }
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
