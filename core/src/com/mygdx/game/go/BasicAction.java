package com.mygdx.game.go;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Constants;
import com.mygdx.game.GameLogic.EvilFactory;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.GameLogic.Player;
import com.mygdx.game.SoundManager;
import com.mygdx.game.go.AuxiliarClasses.Cost;
import com.mygdx.game.go.AuxiliarClasses.QuantityTarget;

public class BasicAction extends GameObject{

    int id;
    Cost cost;
    QuantityTarget money;
    QuantityTarget volunteers;

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void update(float elapsedTime) {

    }


    public void use() {

        SoundManager.getInstance().click.play();

        targetedUse(money);
        targetedUse(volunteers);

        GameManager.getInstance().nextTurn();
    }

    private void targetedUse(QuantityTarget qT)
    {
        System.out.println(qT.target);
        if (qT.target == Constants.SELF)
        {
            GameManager.playerList.get(GameManager.turn).useCard
                    (0, money.quantity, volunteers.quantity);
        }
    }
}
