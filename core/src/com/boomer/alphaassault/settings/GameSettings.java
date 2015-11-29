package com.boomer.alphaassault.settings;

/**
 * Created by Omer on 11/26/2015.
 */
public class GameSettings {

    public static final String TITLE = "ALPHA ASSAULT";


    public static final float RPS = 1 / 120f; //120 RENDER PER SECOND
    public static final float UPS = 1 / 60f; //60 UPDATE PER SECOND
    public static final float IPS = 1 / 180f; //180 INPUT PER SECOND

    //GAME STATES
    public static volatile boolean GAME_RUNNING_STATE = false;
    public static final boolean RUNNING_STATE_ACTIVE = true;
    public static final boolean RUNNING_STATE_INACTIVE = false;


    //TEAM DETAILS
    public static final int TEAM_RED = 0;
    public static final int TEAM_BLUE = 1;
}
