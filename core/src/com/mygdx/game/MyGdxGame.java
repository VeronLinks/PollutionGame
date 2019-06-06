package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.Controllers;
import com.mygdx.game.Controllers.*;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.Screens.EndScreen;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MenuScreen;

/*
	STILL TO DO:
	- We should be able to see enemy's affinity and bankrupt status.
	- Make BasicActions work
	- Make SelfCards not to explode and work instead.
	- When it's the turn of the enemies, Show the card each enemy is going to use and, then, use it (in GameManager, where indicated)
	- Make sure cards work.
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
			Gdx.input.setCatchBackKey(true);
		}

		Gdx.app.debug("CREATE", "GDXGAME");

		Gdx.input.setInputProcessor(input);

		//

		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);

		GameManager.setGame(this);

		setScreen(menuScreen);
//		setScreen(new EndScreen(this, false));
	}
}
