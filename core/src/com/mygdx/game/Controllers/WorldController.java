package com.mygdx.game.Controllers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.go.Background;
import com.mygdx.game.go.Card;
import com.mygdx.game.go.CardFactory;
import com.mygdx.game.go.GameObject;
import com.mygdx.game.hud.GameStats;
import com.mygdx.game.hud.HUD;
import com.mygdx.game.hud.TextButton;

import java.util.ArrayList;

public class WorldController {


    public static OrthographicCamera camera;
    public static OrthographicCamera hudCamera;

    private static WorldController instance;
    private Assets assets = Assets.getInstance();

    Background bg;
    ArrayList<GameObject> cardsOnBoard;

    CardFactory factory;
    HUD hud;
    Card toRemove;
    GameStats gameStats;


    private WorldController(){

        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cardsOnBoard = new ArrayList<GameObject>();

        createMasterDeck();
        createHUD();

        //Gdx.input.setInputProcessor(this);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        init();
    }
    public static WorldController getInstance(){
        if(instance==null){
            instance = new WorldController();
        }
        return instance;
    }

    public static void init(){

    }

    public void update(float dt){

    }

    private void createHUD() {
        hud = new HUD();
        TextButton b1 = new TextButton("CARD", 10, 10, 120, 40) {
            @Override
            public void click() {
                Card c = factory.gimmeRandomCard();
                c.position.x = MathUtils.random(-camera.viewportWidth / 3, camera.viewportWidth / 3);
                c.position.y = MathUtils.random(-camera.viewportHeight / 3, camera.viewportHeight / 3);
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

}
