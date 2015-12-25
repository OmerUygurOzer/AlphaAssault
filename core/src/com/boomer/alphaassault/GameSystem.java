package com.boomer.alphaassault;

/**
 * Created by Omer on 11/26/2015.
 */
public class GameSystem {

    private static short baseReference = Short.MIN_VALUE;

    public static final String TITLE = "ALPHA ASSAULT";


    public static final float RPS = 1 / 120f; //120 RENDER PER SECOND
    public static final float UPS = 1 / 60f; //60 UPDATE PER SECOND
    public static final float IPS = 1 / 120f; //120 INPUT PER SECOND

    //GAME STATES
    public static volatile boolean GAME_RUNNING_STATE = false;
    public static final boolean RUNNING_STATE_ACTIVE = true;
    public static final boolean RUNNING_STATE_INACTIVE = false;


    //TEAM DETAILS
    public static final int TEAM_AI = -1;
    public static final int TEAM_RED = 0;
    public static final int TEAM_BLUE = 1;
    public static final int TEAM_GREEN = 2;
    public static final int TEAM_BROWN = 3;
    public static final int TEAM_YELLOW = 4;

    //CONTROLS
    public static final int INPUT_MAX = 5;

    public static short obtainReference(){
        baseReference++;
        return baseReference;
    }

}
