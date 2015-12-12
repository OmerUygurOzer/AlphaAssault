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

    private class Button{
        private BSprite icon;

        public float centerX;
        public float centerY;

        public int width;
        public int height;

        public Button(TextureRegion _textureRegion){
            icon = new BSprite(_textureRegion);
        }

        public Button(Texture _texture){
            icon = new BSprite(_texture);
        }

    }

    //INPUT MAPPING
    public static final int BUTTON_ONE_STATE = 0;
    public static final int BUTTON_TWO_STATE = 1;
    public static final int BUTTON_THREE_STATE = 2;
    public static final int BUTTON_FOUR_STATE = 3;

    //BUTTON STATES
    public static final boolean IDLE    = false;
    public static final boolean PRESSED = true;

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
    }

    public void addButton(Texture _texture){
        //CREATE BUTTON
        Button button = new Button(_texture);

        //SET BUTTON PROPERTIES
        button.width = CONSOLE_BUTTON_WIDTH;
        button.height = CONSOLE_BUTTON_HEIGHT;
        button.centerX = CONSOLE_BUTTON_X;
        button.centerY = CONSOLE_BUTTON_Y + buttonNumber * 80;
        button.icon.setCenter(button.centerX,button.centerY);
        button.icon.setSize(CONSOLE_BUTTON_WIDTH,CONSOLE_BUTTON_HEIGHT);

        buttons.add(button);
        RenderStateManager.updatingState.addElement(viewType,referenceId + buttonNumber, RenderState.DEPTH_GAME_SCREEN,button.icon);
        buttonNumber++;

    }

    @Override
    public void receiveInput() {
        boolean buttonOneState   = IDLE;
        boolean buttonTwoState   = IDLE;
        boolean buttonThreeState = IDLE;
        boolean buttonFourState  = IDLE;

        for (Long key : Inputs.getInputs().keySet()){
            float inputX = Inputs.getInputs().get(key).x;
            float inputY = Inputs.getInputs().get(key).y;
            for(int index=0;index<buttons.size();index++) {
                Button button = buttons.get(index);
                boolean fitsX = Math.abs(button.centerX - inputX)<button.width/2;
                boolean fitsY = Math.abs(button.centerY - inputY)<button.height/2;
                if(fitsX && fitsY){
                        switch (index){
                            case BUTTON_ONE:
                                buttonOneState = PRESSED;
                                set(BUTTON_ONE_STATE,PRESSED);
                                System.out.println("1");
                                break;
                            case BUTTON_TWO:
                                buttonTwoState = PRESSED;
                                set(BUTTON_TWO_STATE,PRESSED);
                                System.out.println("2");
                                break;
                            case BUTTON_THREE:
                                buttonThreeState = PRESSED;
                                set(BUTTON_THREE_STATE,PRESSED);
                                System.out.println("3");
                                break;
                            case BUTTON_FOUR:
                                buttonFourState = PRESSED;
                                set(BUTTON_FOUR_STATE,PRESSED);
                                System.out.println("4");
                                break;
                            default:
                                //DO NOTHING
                                break;
                        }
                }
            }

        }

        if(buttonOneState == IDLE){set(BUTTON_ONE_STATE,IDLE);}
        if(buttonTwoState == IDLE){set(BUTTON_TWO_STATE,IDLE);}
        if(buttonThreeState == IDLE){set(BUTTON_THREE_STATE,IDLE);}
        if(buttonFourState == IDLE){set(BUTTON_FOUR_STATE,IDLE);}
    }

    @Override
    public void addToRenderState() {

    }

    @Override
    public long getReferenceID() {
        return 0;
    }

    @Override
    public void setReferenceID(long _referenceId) {

    }

    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }

}
