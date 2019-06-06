package com.mygdx.game.GameLogic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.go.GameObject;

public class Player extends GameObject
{
    public int pollution, money, volunteers;

    public Player()
    {
        pollution = 0;
        money = 2;
        volunteers = 1;
    }

    public void useCard(int pollution, int money, int volunteers)
    {
        this.pollution += pollution;
        this.money += money;
        this.volunteers += volunteers;
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void update(float elapsedTime) {

    }
}
