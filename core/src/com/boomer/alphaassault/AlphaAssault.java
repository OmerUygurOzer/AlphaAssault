package com.boomer.alphaassault;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.resources.Resource;

public class AlphaAssault extends Game {

	public static final String TITLE = "ALPHA ASSAULT";


	private GameStateManager gameStateManager;
	private Resource gameResources;

	public static final float FPS = 1 / 60f; //60 FRAMES/SEC MAX
	private float accum;


	public static SpriteBatch mainSpriteBatch;

	
	@Override
	public void create () {

		mainSpriteBatch = new SpriteBatch();

		gameResources = new Resource();
		gameResources.initialize();
		gameStateManager = new GameStateManager(this);
	}

	@Override
	public void render () {
		super.render();
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= FPS){
			accum -= FPS;

			gameStateManager.update(FPS);
			gameStateManager.render();

		}
	}



}
