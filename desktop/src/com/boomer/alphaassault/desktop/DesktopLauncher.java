package com.boomer.alphaassault.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.boomer.alphaassault.AlphaAssault;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = AlphaAssault.VIRTUAL_HEIGHT * AlphaAssault.SCALE;
		config.width = AlphaAssault.VIRTUAL_WIDTH * AlphaAssault.SCALE;
		new LwjglApplication(new AlphaAssault(), config);
	}
}
