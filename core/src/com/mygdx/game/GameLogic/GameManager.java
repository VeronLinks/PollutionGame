package com.mygdx.game.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Constants;
import com.mygdx.game.Controllers.WorldController;
import com.mygdx.game.go.Card;
import com.mygdx.game.go.CardFactory;
import com.mygdx.game.go.GameObject;
import com.mygdx.game.hud.GameStats;
import com.mygdx.game.hud.HUD;
import com.mygdx.game.hud.TextButton;

import java.util.ArrayList;

public class GameManager
{
    private static GameManager instance;

    public ArrayList<Player> playerList;
    public ArrayList<EvilFactory> eFactoryList;
    public ArrayList<GameObject> cardsOnBoard;
    public int turn;
    public static int jugadores;
    public HUD hud;
    public GameStats gameStats;

    CardFactory factory;
    WorldController WC = WorldController.getInstance();

    private GameManager(int jugadores)
    {
        turn = 0;
        this.jugadores=jugadores;

        eFactoryList = new ArrayList<EvilFactory>();
        playerList = new ArrayList<Player>();
        cardsOnBoard = new ArrayList<GameObject>();

        for(int i=0;i<jugadores;i++)
        {
            playerList.add(new Player());
        }

        for(int i=0;i< Constants.NUMBER_EVIL_FACTORIES;i++)
        {
            eFactoryList.add(new EvilFactory());
        }

        createMasterDeck();
        createHUD();
    }

    public static GameManager getInstance()
    {
        if(instance==null){
            instance = new GameManager(jugadores);
        }
        return instance;
    }

    public void nextTurn()
    {
        if(turn==jugadores-1)
        {
            EvilTurn();
        }
            turn+=1%jugadores;
    }

    public void EvilTurn()
    {
        for(int i=0;i<Constants.NUMBER_EVIL_FACTORIES;i++)
        {
            //aqui se mete el uso de la carta que usa la factory.
        }
    }

    private void createMasterDeck()
    {
        Json json = new Json();
        factory = json.fromJson(CardFactory.class, Gdx.files.internal("example.json"));
        System.out.println(factory.cards.size() + " cards in the master deck");
    }

    private void createHUD()
    {
        hud = new HUD();
        TextButton b1 = new TextButton("CARD", 10, 10, 120, 40) {
            @Override
            public void click() {
                Card c = factory.gimmeRandomCard();
                c.position.x = MathUtils.random(-WC.camera.viewportWidth / 3, WC.camera.viewportWidth / 3);
                c.position.y = MathUtils.random(-WC.camera.viewportHeight / 3, WC.camera.viewportHeight / 3);
                c.init();
                cardsOnBoard.add(c);
            }
        };
        hud.add(b1);
        gameStats = new GameStats(200, 40);
        hud.add(gameStats);
    }
}
