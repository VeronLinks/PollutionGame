package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.go.Background;
import com.mygdx.game.go.Card;
import com.mygdx.game.go.CardFactory;
import com.mygdx.game.go.GameObject;
import com.mygdx.game.hud.GameStats;
import com.mygdx.game.hud.HUD;
import com.mygdx.game.hud.TextButton;

import java.util.ArrayList;

public class MyGdxGame  extends InputAdapter implements ApplicationListener {

	OrthographicCamera camera1;
	OrthographicCamera hudCamera;

	CardFactory factory;

	Background bg;
	ArrayList<GameObject> cardsOnBoard;

	HUD hud;

	SpriteBatch batch;


	Card toRemove;
	com.mygdx.game.hud.GameStats gameStats;

	@Override
	public void create() {

		createMasterDeck();

		camera1 = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		bg = new Background();
		cardsOnBoard = new ArrayList<GameObject>();

		batch = new SpriteBatch();

		createHUD();

		Gdx.input.setInputProcessor(this);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	private void createHUD() {
		hud = new HUD();
		TextButton b1 = new TextButton("CARD", 10, 10, 120, 40) {
			@Override
			public void click() {
				Card c = factory.gimmeRandomCard();
				c.position.x = MathUtils.random(-camera1.viewportWidth / 3, camera1.viewportWidth / 3);
				c.position.y = MathUtils.random(-camera1.viewportHeight / 3, camera1.viewportHeight / 3);
				c.init();
				cardsOnBoard.add(c);
			}
		};
		hud.add(b1);
		gameStats = new GameStats(200, 40);
		hud.add(gameStats);
	}

	private void createMasterDeck() {
		Json json = new Json();
		factory = json.fromJson(CardFactory.class,
				Gdx.files.internal("example.json"));

		System.out.println(factory.cards.size() + " cards in the master deck");
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(0f, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//render GOs
		batch.setProjectionMatrix(camera1.combined);
		batch.begin();
		bg.render(batch);
		for (GameObject go : cardsOnBoard) {
			go.render(batch);
		}
		batch.end();

		//render HUD
		batch.setProjectionMatrix(hudCamera.combined);
		batch.begin();
		hud.render(batch);
		batch.end();

	}

	public void resize(int width, int height) {

		System.out.println("Resize to:" + width + "x" + height);

		camera1.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		camera1.update();

		hudCamera.viewportWidth = width;
		hudCamera.viewportHeight = height;

		//0,0 in the lower left corner
		hudCamera.position.x = width / 2;
		hudCamera.position.y = height / 2;
		hudCamera.update();
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
		}
		return true;
	}
}
