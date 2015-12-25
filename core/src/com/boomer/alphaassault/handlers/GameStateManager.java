package com.boomer.alphaassault.handlers;

import com.boomer.alphaassault.gamestates.GameState;
import com.boomer.alphaassault.gamestates.Play;

import java.util.Stack;

/**
 * Created by Omer on 11/24/2015.
 */
public class GameStateManager {

    private Stack<GameState> GAME_STATES;
    public static final int PLAY = 0;

    public GameStateManager (){

        GAME_STATES = new Stack<GameState>();
        pushState(PLAY);

    }

    public void update(float deltaTime){
            GAME_STATES.peek().update(deltaTime);
    }

    private GameState getState(int _state){
        if(_state == PLAY){return new Play(this);}
        return null;
    }

    public void setState(int _state){
        popState();
        pushState(_state);
    }

    public void pushState(int _state){
        GAME_STATES.push(getState(_state));
    }

    public void popState(){
        GameState gameStateBase = GAME_STATES.pop();
        gameStateBase.dispose();
    }

    public void reSize(int _width,int _height){GAME_STATES.peek().reSize(_width,_height);}

    public GameState getGameState(){
        return GAME_STATES.peek();
    }



}
