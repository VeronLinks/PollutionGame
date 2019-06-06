package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.Controllers;
import com.mygdx.game.Controllers.*;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MenuScreen;

/*
	STILL TO DO:
	- When pressing a card it should call its method use();
	- When it's the turn of the enemies, Show the card each enemy is going to use and, then, use it (in GameManager, where indicated)
 */

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
		//setScreen(new EndScreen(this, false));
	}
}
