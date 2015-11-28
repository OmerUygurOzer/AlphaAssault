package com.boomer.alphaassault.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.boomer.alphaassault.AlphaAssault;
import com.boomer.alphaassault.graphics.GameGraphics;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = GameGraphics.VIRTUAL_HEIGHT;
		config.width = GameGraphics.VIRTUAL_WIDTH;
		new LwjglApplication(new AlphaAssault(), config);
	}
}
