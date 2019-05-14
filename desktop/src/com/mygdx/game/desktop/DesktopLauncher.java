package com.mygdx.game.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Graphics.DisplayMode desktop =
				LwjglApplicationConfiguration.getDesktopDisplayMode();
		//config.setFromDisplayMode(desktop);
		config.fullscreen = false;
		config.width = desktop.width-10;
		config.height = desktop.height-200;
		config.x = 0;
		config.y = 0;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
