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
        if(this.affinity<1){
            this.affinity=1;
        }
        if(this.affinity>6){
            this.affinity=6;
        }
        this.bankruptcy += bankruptcy;
        if(this.bankruptcy<1){
            this.bankruptcy=1;
        }
        if(this.bankruptcy>6){
            this.bankruptcy=6;
        }
    }

    @Override
    public void update(float elapsedTime) {

    }
}
