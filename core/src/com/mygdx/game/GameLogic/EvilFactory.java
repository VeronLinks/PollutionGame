package com.mygdx.game.GameLogic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.go.GameObject;

public class EvilFactory extends GameObject
{
    public int affinity, bankruptcy;

    public EvilFactory()
    {
        affinity = 1;
        bankruptcy = 1;
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    public void useCard(int affinity, int bankruptcy)
    {
        this.affinity += affinity;
        this.bankruptcy += bankruptcy;
    }

    @Override
    public void update(float elapsedTime) {

    }
}
