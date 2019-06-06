package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.Controllers;
import com.mygdx.game.Controllers.*;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MenuScreen;

public class MyGdxGame extends Game {

	public WorldController controller;
	public WorldRenderer renderer;
	public InputController input;
	//
	public MenuScreen menuScreen;
	public GameScreen gameScreen;

	@Override
	public void create() {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		controller = WorldController.getInstance();
		renderer = new WorldRenderer(controller);
		SoundManager.getInstance();

		if(Gdx.app.getType() == Application.ApplicationType.Desktop){

			if(Controllers.getControllers().size > 0){

				ArcadeInputHandler arcade = new ArcadeInputHandler(controller);
				input = arcade;
				Controllers.addListener(arcade);
			}
			else{

				input = new DesktopInputHandler(controller);
			}
		}else{

			input = new MobileInputHandler(controller);
		}

		Gdx.app.debug("CREATE", "GDXGAME");

		Gdx.input.setInputProcessor(input);

		//

		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);

		setScreen(menuScreen);
	}
}
