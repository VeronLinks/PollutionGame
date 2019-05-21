package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Controllers.*;
import com.mygdx.game.go.Background;
import com.mygdx.game.go.Card;
import com.mygdx.game.go.CardFactory;
import com.mygdx.game.go.GameObject;
import com.mygdx.game.hud.GameStats;
import com.mygdx.game.hud.HUD;
import com.mygdx.game.hud.TextButton;

import java.util.ArrayList;

public class MyGdxGame  extends InputAdapter implements ApplicationListener {

	WorldController controller;
	WorldRenderer renderer;
	InputController input;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		controller = WorldController.getInstance();
		renderer = new WorldRenderer(controller);


		if(Gdx.app.getType() == Application.ApplicationType.Desktop){

			if(Controllers.getControllers().size > 0){

				ArcadeInputHandler arcade = new ArcadeInputHandler(controller);
				input = arcade;
				Controllers.addListener(arcade);
			}else{

				input = new DesktopInputHandler(controller);
			}
		}else{

			input = new MobileInputHandler(controller);
		}

		Gdx.input.setInputProcessor(input);


		Gdx.app.debug("CREATE", "");

		Gdx.input.setInputProcessor(input);
	}


	@Override
	public void render() {

		Gdx.gl.glClearColor(0f, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		input.update(Gdx.graphics.getDeltaTime());
		controller.update(Gdx.graphics.getDeltaTime());

		renderer.render();

	}

	public void resize(int width, int height) {
		renderer.resize(width, height);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	//those are created outside the method, to avoid Garbage generation!
	// they exist during the whole execution of the game
	Vector3 pointHUD;
	Vector3 pointGame;

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
/*
		//screen touched, corresponds to a HUD button?
		pointHUD = new Vector3(screenX, screenY, 0);

		//check if the click is for the HUD
		hudCamera.unproject(pointHUD);
		if (!hud.click(pointHUD.x, pointHUD.y)) {

			//the click is not for the HUD, check if it is for the cards!
			pointGame = new Vector3(screenX, screenY, 0);
			camera1.unproject(pointGame);

			for (GameObject card : cardsOnBoard) {

				if (card instanceof Card && card.getBounds().contains(pointGame.x, pointGame.y)) {
					//card clicked, "use" it and remove it afterwards
					toRemove = (Card) card;
					toRemove.use(gameStats);
				}
			}
		}
		if (toRemove != null) {
			cardsOnBoard.remove(toRemove);
			toRemove = null;
		}*/
		return true;
	}
}
