package com.mygdx.game.go;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.GameLogic.EvilFactory;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.GameLogic.Player;
import com.mygdx.game.SoundManager;
import com.mygdx.game.go.AuxiliarClasses.QuantityTarget;
import com.mygdx.game.hud.GameStats;

public class FactoryCard extends GameObject {

    int id;
    String name;
    String description;
    QuantityTarget money;
    QuantityTarget volunteers;
    QuantityTarget pollution;
    QuantityTarget affinity;
    QuantityTarget bankrupty;
    private Rectangle imageBounds;
    private Rectangle textBounds;
    private int factoryNumber;

    public void setFactoryNumber(int factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    private GameManager GM;
    /**
     * will be called by the JSON populator
     */
    public FactoryCard() {
        super();
        dimension.x = 200;
        dimension.y = 300;
        //System.out.println("Card constructor called");
    }

    /**
     * manually called, as the Json builder is calling the default constructor without parameters.
     */
    public void init() {
        imageBounds = new Rectangle(position.x + dimension.x * 0.1f, position.y + dimension.y * 0.45f, dimension.x * 0.8f, dimension.y * 0.50f);
        textBounds = new Rectangle(position.x + dimension.x * 0.1f, position.y + dimension.y * 0.05f, dimension.x * 0.8f, dimension.y * 0.35f);
        GM = GameManager.getInstance();
    }

    public void use() {

        SoundManager.getInstance().click.play();

        targetedUse(money);
        targetedUse(volunteers);
        targetedUse(pollution);
        targetedUse(affinity);
        targetedUse(bankrupty);

        active = false;
    }

    private void targetedUse(QuantityTarget qT)
    {
        if (qT.target.equals(Constants.SELF))
        {
            GameManager.playerList.get(GameManager.turn).useCard
                    (pollution.quantity, money.quantity, volunteers.quantity);
        }
        else if (qT.target.equals(Constants.FIRST_PLAYER))
        {
            GameManager.playerList.get(GameManager.startedLastTurn).useCard
                    (pollution.quantity, money.quantity, volunteers.quantity);
        }
        else if (qT.target.equals(Constants.PLAYERS))
        {
            for (Player p : GameManager.playerList)
            {
                p.useCard(pollution.quantity, money.quantity, volunteers.quantity);
            }
        }
        else if (qT.target.equals(Constants.FACTORY))
        {
            GameManager.eFactoryList.get(factoryNumber).useCard
                    (affinity.quantity, bankrupty.quantity);
        }
        else if (qT.target.equals(Constants.FACTORIES))
        {
            for (EvilFactory f : GameManager.eFactoryList)
            {
                f.useCard(affinity.quantity, bankrupty.quantity);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        //color for the card background, the card assets is white and then tinted
        //batch.setColor(c);
        batch.draw(Assets.getInstance().card_factory, position.x, position.y, dimension.x, dimension.y);
        batch.setColor(Color.WHITE);

        //image area
        //batch.draw(Assets.getInstance().black, imageBounds.x, imageBounds.y, imageBounds.width, imageBounds.height);
        //batch.draw(Assets.getInstance().getTexture(tex), imageBounds.x, imageBounds.y, imageBounds.width, imageBounds.height);

        //text area
        Assets.getInstance().GameFont.setColor(Color.BLACK);
        Assets.getInstance().GameFont.draw(batch, name, textBounds.x, textBounds.y + textBounds.height * 2.13f, textBounds.width, Align.center, true);
        Assets.getInstance().GameFont.draw(batch, description, textBounds.x, textBounds.y + textBounds.height * 1.65f, textBounds.width, Align.center, true);

    }

    @Override
    public void update(float elapsedTime) {

    }
}
