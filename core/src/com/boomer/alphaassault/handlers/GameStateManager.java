package com.boomer.alphaassault.handlers;

import com.badlogic.gdx.Game;
import com.boomer.alphaassault.AlphaAssault;
import com.boomer.alphaassault.gamestates.GameStateBase;
import com.boomer.alphaassault.gamestates.Play;
import com.boomer.alphaassault.resources.Resource;

import java.util.Stack;

/**
 * Created by Omer on 11/24/2015.
 */
public class GameStateManager {
    public Game game;
    private Stack<GameStateBase> gameStates;
    private Resource gameResources;

    public static final int PLAY = 0;

    public GameStateManager (Game _game){
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

    public void reSize(int _width,int _height){
        gameStates.peek().reSize(_width,_height);
    }

}
