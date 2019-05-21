package com.mygdx.game.GameLogic;

import java.util.ArrayList;

public class GameManager
{
    private static GameManager instance;

    Player player1,player2;
    EvilFactory eFactory1,eFactory2,eFactory3,eFactory4;
    ArrayList<Members> gameTurn;
    int turn;

    private GameManager()
    {
        turn = 0;

        player1 = new Player();
        player2 = new Player();

        eFactory1 = new EvilFactory();
        eFactory2 = new EvilFactory();
        eFactory3 = new EvilFactory();
        eFactory4 = new EvilFactory();

        gameTurn.add(player1);
        gameTurn.add(player2);
        gameTurn.add(eFactory1);
        gameTurn.add(eFactory2);
        gameTurn.add(eFactory3);
        gameTurn.add(eFactory4);
    }

    public static GameManager getInstance(){
        if(instance==null){
            instance = new GameManager();
        }
        return instance;
    }

    public void update(float dt)
    {

    }

    public void nextTurn()
    {
        turn+=1%6;
    }

}
