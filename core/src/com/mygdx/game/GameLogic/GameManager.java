package com.mygdx.game.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Constants;
import com.mygdx.game.Controllers.WorldController;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.EndScreen;
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
    public static ArrayList<GameObject> basicActions;
    public static int turn;
    public static int eFactoryTurn;
    public static int players = 1;
    public static HUD hud,pauseHUD;
    public static GameStats gameStats;
    public static Player currentPlayer;
    public static int startedLastTurn;
    public static int numberOfTurns;
    public static ArrayList<GameObject> currentSpecialCard;
    public static MyGdxGame game;
    public static boolean isPaused;
    public static int selfCardTurn = 0;

    CardFactory factory;
    WorldController WC = WorldController.getInstance();

    // States for the turns
    public static int state;
    public static final int STATE_NONE = 0;
    public static final int STATE_PLAYER_ACTIONS = 1;
    public static final int STATE_GAME_ACTIONS = 2;

    public static void setGame(MyGdxGame game) {
        GameManager.game = game;
    }

    private GameManager()
    {
        isPaused = false;
        turn = 0;
        numberOfTurns = 1;
        startedLastTurn = 0;
        state = STATE_NONE;

        eFactoryList = new ArrayList<EvilFactory>();
        playerList = new ArrayList<Player>();
        cardsOnBoard = new ArrayList<GameObject>();
        basicActions = new ArrayList<GameObject>();
        currentSpecialCard = new ArrayList<GameObject>();

        for(int i = 0;i < Constants.NUMBER_EVIL_FACTORIES;i++)
        {
            eFactoryList.add(new EvilFactory());
        }

        int actions = 4;
        for(int i=0; i<actions; i++){

        }

        hud = new HUD();

        createPauseHUD();
    }

    public void gameInit(int numOfPlayers)
    {
        players = numOfPlayers;

        playerList.clear();
        for(int i = 0; i < players; i++)
        {
            playerList.add(new Player());
        }

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
        // CHECK VICOTRY / LOSS CONDITIONS:
        if (allFactoriesLost())
        {
            game.setScreen(new EndScreen(game, true));
        }
        else if (allPlayersLost())
        {
            game.setScreen(new EndScreen(game, false));
        }

        // NOW GO ON WITH TURNS
        if(numberOfTurns >= players)
        {
            EvilTurn();
        }
        else {
            PlayerTurn();
            numberOfTurns++;
        }
    }

    private boolean allFactoriesLost()
    {
        for (EvilFactory f : eFactoryList) {
            if (f.affinity < Constants.MAX_AFFINITY && f.bankruptcy < Constants.MAX_BANKRUP)
            {
                return false;
            }
        }
        return true;
    }
    private boolean allPlayersLost()
    {
        for (Player p : playerList) {
            if (p.pollution < Constants.MAX_POLLUTION)
            {
                return false;
            }
        }
        return true;
    }

    public void PlayerTurn()
    {
        turn++;
        if (turn == players)
        {
            turn = 0;
        }
        currentPlayer = playerList.get(turn);
    }

    private void EvilTurn()
    {
        currentSpecialCard.clear();
        state = STATE_GAME_ACTIONS;
        for(eFactoryTurn = 0; eFactoryTurn < Constants.NUMBER_EVIL_FACTORIES; eFactoryTurn++)
        {
            if (eFactoryList.get(eFactoryTurn).affinity < Constants.MAX_AFFINITY)
            {
                currentSpecialCard.add(factory.gimmeRandomFactoryCard());
            }
        }
        // Check somewhere these conditions. If state, thow the cards in currentSpecialCard.
        // When clicking a card, use and deactivate.
        // If state & every card is deactivated, then newturn
        // Same logic with selfcards
    }

    public void newTurn()
    {
        numberOfTurns = 0;
        startedLastTurn += 1;
        if (startedLastTurn == players)
        {
            startedLastTurn = 0;
        }
        fillBoard();
        currentSpecialCard.clear();
        state = STATE_PLAYER_ACTIONS;
        selfCardTurn = 0;
        for(int turn = 0; turn < players; turn++)
        {
            if (playerList.get(turn).pollution < Constants.MAX_POLLUTION)
            {
                currentSpecialCard.add(factory.gimmeRandomSelfCard());
            }
        }
        turn = startedLastTurn;
    }

    public void deleteUsedCard(Vector2 position)
    {
        for (int i = 0; i < Constants.MAX_CARDS; i++)
        {
            if (cardsOnBoard.get(i) instanceof SelectedCard && cardsOnBoard.get(i).position == position)
            {
                System.out.println("CARD DELETED");
                cardsOnBoard.remove(i);
                return;
            }
        }
    }

    private void createMasterDeck()
    {
        Json json = new Json();
        factory = json.fromJson(CardFactory.class, Gdx.files.internal("card_base.json"));
        System.out.println(factory.self_cards.size() + " cards in the self_cards deck");
        System.out.println(factory.selected_cards.size() + " cards in the selected_cards deck");
        System.out.println(factory.factory_cards.size() + " cards in the factory_cards deck");
    }

    private void fillBoard()
    {
        int x = (int)(-WC.camera.viewportWidth/6.5f);
        int y = (int)-WC.camera.viewportHeight/10;
        int offset = (int)(WC.camera.viewportWidth/5.5f);
        cardsOnBoard.clear();
        for (int i = 0; i < Constants.MAX_CARDS; i++){
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
    }

    private void fillHUD()
    {
        float margin = 30;

        fillBoard();

        for(BasicAction b : factory.basicActions){
            basicActions.add(b);
        }
        /*TextButton b1 = new TextButton("CARD", -WC.hudCamera.viewportWidth/2 + margin,
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
        hud.add(b1);*/
        gameStats = new GameStats(-WC.hudCamera.viewportWidth/2 + 240 + margin * 2,
                -WC.hudCamera.viewportHeight/2 + 40 + margin);
        hud.add(gameStats);
    }

    public void createPauseHUD()
    {
        pauseHUD = new HUD();
        TextButton b1 = new TextButton("Main Menu", -150, 10, 150, 40) {
            @Override
            public void click()
            {
                isPaused = false;
                game.setScreen(game.menuScreen);
            }
        };
        pauseHUD.add(b1);
        TextButton b2 = new TextButton("Resume", 50, 10, 120, 40) {
            @Override
            public void click() { isPaused = false; }
        };
        pauseHUD.add(b2);
    }
}
