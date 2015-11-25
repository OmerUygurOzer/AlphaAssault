package com.boomer.alphaassault.handlers;

import com.boomer.alphaassault.AlphaAssault;
import com.boomer.alphaassault.gamestates.GameStateBase;
import com.boomer.alphaassault.gamestates.Play;

import java.util.Stack;

/**
 * Created by Omer on 11/24/2015.
 */
public class GameStateManager {
    public AlphaAssault game;
    private Stack<GameStateBase> gameStates;

    public static final int PLAY = 0;

    public GameStateManager (AlphaAssault _game){
        this.game = _game;
        gameStates = new Stack<GameStateBase>();
        pushState(PLAY);

    }

    public void update(float deltaTime){
            gameStates.peek().update(deltaTime);
    }

    public void render(){
            gameStates.peek().render();
    }

    private GameStateBase getState(int _state){
        if(_state == PLAY){return new Play(this);}
        return null;
    }

    public void setState(int _state){
        popState();
        pushState(_state);
    }

    public void pushState(int _state){
        gameStates.push(getState(_state));
    }

    public void popState(){
        GameStateBase gameStateBase = gameStates.pop();
        gameStateBase.dispose();
    }

}
