package com.mygdx.game.GameLogic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.go.AuxiliarClasses.Cost;
import com.mygdx.game.go.GameObject;

public class Player extends GameObject
{
    public int pollution, money, volunteers;

    public Player()
    {
        pollution = 1;
        money = 2;
        volunteers = 1;
    }

    public boolean canAfford(Cost cost)
    {
        if (money >= cost.money && volunteers >= cost.volunteers)
        {
            money -= cost.money;
            return true;
        }
        return false;
    }

    public void useCard(int pollution, int money, int volunteers)
    {
        this.pollution += pollution;
        this.money += money;
        this.volunteers += volunteers;

        if (this.money < 0)
        {
            this.money = 0;
        }
        if (this.volunteers < 1)
        {
            this.volunteers = 1;
        }
        if (this.pollution < 1)
        {
            this.pollution = 1;
        }
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void update(float elapsedTime) {

    }
}
