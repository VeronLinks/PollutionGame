package com.mygdx.game.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Constants;
import com.mygdx.game.Controllers.WorldController;
import com.mygdx.game.go.*;
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
    public static int players = 1;
    public HUD hud;
    public GameStats gameStats;

    CardFactory factory;
    WorldController WC = WorldController.getInstance();

    private GameManager()
    {
        turn = 0;

        eFactoryList = new ArrayList<EvilFactory>();
        playerList = new ArrayList<Player>();
        cardsOnBoard = new ArrayList<GameObject>();

        for(int i = 0; i < players; i++)
        {
            playerList.add(new Player());
        }

        for(int i=0;i < Constants.NUMBER_EVIL_FACTORIES;i++)
        {
            eFactoryList.add(new EvilFactory());
        }

        hud = new HUD();
    }

    public void gameInit(int numOfPlayers)
    {
        players = numOfPlayers;
        createMasterDeck();
        fillHUD();
    }

    public static GameManager getInstance()
    {
        if(instance==null){
            instance = new GameManager();
        }
        return instance;
    }

    public void nextTurn()
    {
        if(turn == players)
        {
            EvilTurn();
        }
        else {
            PlayerTurn();
            turn += 1;
        }
    }

    private void PlayerTurn()
    {

    }

    private void EvilTurn()
    {
        for(int i=0; i < Constants.NUMBER_EVIL_FACTORIES; i++)
        {
            //aqui se mete el uso de la carta que usa la factory.
        }
        turn = 0;
    }

    private void createMasterDeck()
    {
        Json json = new Json();
        factory = json.fromJson(CardFactory.class, Gdx.files.internal("card_base.json"));
        System.out.println(factory.self_cards.size() + " cards in the self_cards deck");
        System.out.println(factory.selected_cards.size() + " cards in the selected_cards deck");
        System.out.println(factory.factory_cards.size() + " cards in the factory_cards deck");
    }

    private void fillHUD()
    {
        float margin = 30;
        TextButton b1 = new TextButton("CARD", -WC.hudCamera.viewportWidth/2 + margin,
                -WC.hudCamera.viewportHeight/2 + margin/2, 240, 80) {
            @Override
            public void click() {
                FactoryCard c = factory.gimmeRandomFactoryCard();
                c.position.x = MathUtils.random(-WC.camera.viewportWidth / 3, WC.camera.viewportWidth / 3);
                c.position.y = MathUtils.random(-WC.camera.viewportHeight / 3, WC.camera.viewportHeight / 3);
                c.init();
                cardsOnBoard.add(c);
            }
        };
        hud.add(b1);
        gameStats = new GameStats(-WC.hudCamera.viewportWidth/2 + 240 + margin * 3, -WC.hudCamera.viewportHeight/2 + 40 + margin/2);
        hud.add(gameStats);
    }
}
