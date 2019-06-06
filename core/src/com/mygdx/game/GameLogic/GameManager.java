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

    public static ArrayList<Player> playerList;
    public static ArrayList<EvilFactory> eFactoryList;
    public static ArrayList<GameObject> cardsOnBoard;
    public static int turn;
    public static int eFactoryTurn;
    public static int players = 1;
    public static HUD hud;
    public static GameStats gameStats;
    public static Player currentPlayer;
    public static int startedLastTurn;

    private static int numberOfTurns;

    CardFactory factory;
    WorldController WC = WorldController.getInstance();

    private GameManager()
    {
        turn = 0;
        numberOfTurns = 0;

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
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    public void nextTurn()
    {
        if(numberOfTurns == players)
        {
            EvilTurn();
        }
        else {
            PlayerTurn();
        }
    }

    public void PlayerTurn()
    {
        if (turn == players)
        {
            turn = 0;
        }
        currentPlayer = playerList.get(turn);
        turn++;
    }

    private void EvilTurn()
    {
        for(eFactoryTurn = 0; eFactoryTurn < Constants.NUMBER_EVIL_FACTORIES; eFactoryTurn++)
        {
            if (eFactoryList.get(eFactoryTurn).affinity < Constants.MAX_AFFINITY)
            {
                // AQUÍ METER LA LLAMADA A LA CARTA DE FACTORÍA Y SU USO
            }
        }
        numberOfTurns = 0;
        startedLastTurn += 1;
        if (startedLastTurn == players)
        {
            startedLastTurn = 0;
        }
        turn = startedLastTurn;
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
        int maxCards = 6;
        int x = (int)(-WC.camera.viewportWidth/6.5f);
        int y = (int)-WC.camera.viewportHeight/10;
        int offset = (int)(WC.camera.viewportWidth/5.5f);
        for (int i=0;i<maxCards; i++){
            SelectedCard c = factory.gimmeRandomSelectedCard();
            c.position.x = x + offset*(i%3);
            if(i<3){
                c.position.y = y;
            }else{
                c.position.y = y - offset*1.1f;
            }
            c.init();
            cardsOnBoard.add(c);
        }
        TextButton b1 = new TextButton("CARD", -WC.hudCamera.viewportWidth/2 + margin,
                -WC.hudCamera.viewportHeight/2 + margin/2, 240, 80) {
            @Override
            public void click() {
                SelectedCard c = factory.gimmeRandomSelectedCard();
                c.position.x = MathUtils.random(-WC.camera.viewportWidth / 3, WC.camera.viewportWidth / 3);
                c.position.y = MathUtils.random(-WC.camera.viewportHeight / 3, WC.camera.viewportHeight / 3);
                c.init();
                cardsOnBoard.add(c);
            }
        };
        hud.add(b1);
        gameStats = new GameStats(-WC.hudCamera.viewportWidth/2 + 240 + margin * 2,
                -WC.hudCamera.viewportHeight/2 + 40 + margin);
        hud.add(gameStats);
    }
}
