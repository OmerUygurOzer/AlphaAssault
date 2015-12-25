package com.boomer.alphaassault.handlers.controls;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.utilities.GameMath;

/**
 * Created by Omer on 12/24/2015.
 */
public class Analog extends Controller {

    private int TYPE;

    public static final int LEFT_ONLY = 0;
    public static final int RIGHT_ONLY = 1;
    public static final int BOTH = 2;

    private static final int BUTTON_SIZE = 40;
    private static final int CIRCLE_SIZE = 180;
    private static final int RADIUS = 90;

    private static final Vector2 LEFT_BUTTON_CENTER = new Vector2(100,100);
    private static final Vector2 RIGHT_BUTTON_CENTER = new Vector2(GameGraphics.VIRTUAL_WIDTH-100,100);

    private Vector2 leftCurrentLocation;
    private Vector2 rightCurrentLocation;

    //INPUT MAPPING
    public static final int LEFT_ANALOG    = 0;
    public static final int LEFT_ROTATION  = 1;
    public static final int LEFT_ACTIVE    = 2;
    public static final int RIGHT_ANALOG   = 3;
    public static final int RIGHT_ROTATION = 4;
    public static final int RIGHT_ACTIVE   = 5;

    private boolean leftActive;
    private boolean rightActive;

    public Analog(int _type){
        super();
        TYPE = _type;

        leftCurrentLocation = new Vector2(LEFT_BUTTON_CENTER);
        rightCurrentLocation = new Vector2(RIGHT_BUTTON_CENTER);

        leftActive = false;
        rightActive = false;
    }

    @Override
    public void receiveInput() {
        if(TYPE == LEFT_ONLY || TYPE == BOTH) {
            updateLeft();
        }
        if(TYPE == RIGHT_ONLY || TYPE == BOTH) {
            updateRight();
        }
    }

    protected void updateLeft(){
        for (Long key : Inputs.getInputs().keySet()){
            float inputX = Inputs.getInputs().get(key).x;
            float inputY = Inputs.getInputs().get(key).y;
            double distance = GameMath.getDistance(inputX,inputY, LEFT_BUTTON_CENTER.x,LEFT_BUTTON_CENTER.y);
            if( distance<= RADIUS){
                leftCurrentLocation.x = inputX;
                leftCurrentLocation.y = inputY;
                set(LEFT_ANALOG,distance*1/RADIUS);
                set(LEFT_ROTATION,GameMath.getAngle(inputX,inputY,LEFT_BUTTON_CENTER.x,LEFT_BUTTON_CENTER.y));
                set(LEFT_ACTIVE,true);
                leftActive = true;
                return;
            }


        }
        set(LEFT_ANALOG,0);
        set(LEFT_ROTATION,0);
        set(LEFT_ACTIVE,false);
        leftActive = false;
        leftCurrentLocation.x = LEFT_BUTTON_CENTER.x;
        leftCurrentLocation.y = LEFT_BUTTON_CENTER.y;

    }

    protected void updateRight(){
        for (Long key : Inputs.getInputs().keySet()){
            float inputX = Inputs.getInputs().get(key).x;
            float inputY = Inputs.getInputs().get(key).y;
            double distance = GameMath.getDistance(inputX,inputY, RIGHT_BUTTON_CENTER.x,RIGHT_BUTTON_CENTER.y);
            if( distance<= RADIUS){
                rightCurrentLocation.x = inputX;
                rightCurrentLocation.y = inputY;
                set(RIGHT_ANALOG,distance*1/RADIUS);
                set(RIGHT_ROTATION, GameMath.getAngle(inputX,inputY,RIGHT_BUTTON_CENTER.x,RIGHT_BUTTON_CENTER.y));
                set(RIGHT_ACTIVE,true);
                leftActive = true;
                return;
            }


        }
        set(RIGHT_ANALOG,0);
        set(RIGHT_ROTATION,0);
        set(RIGHT_ACTIVE,false);
        rightActive = false;
        rightCurrentLocation.x = RIGHT_BUTTON_CENTER.x;
        rightCurrentLocation.y = RIGHT_BUTTON_CENTER.y;


    }
}
