package com.mygdx.game.go;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.hud.GameStats;

public class Card extends GameObject {

    String name;
    String text;
    float cardR;
    float cardG;
    float cardB;
    float money;
    float pollution;
    String tex;
    Color c;
    private Rectangle imageBounds;
    private Rectangle textBounds;

    /**
     * will be called by the JSON populator
     */
    public Card() {
        super();
        dimension.x = 200;
        dimension.y = 300;
        System.out.println("Card constructor called");
    }

    /**
     * manually called, as the Json builder is calling the default constructor without parameters.
     */
    public void init() {
        c = new Color(cardR, cardG, cardB, 1f);
        imageBounds = new Rectangle(position.x + dimension.x * 0.1f, position.y + dimension.y * 0.45f, dimension.x * 0.8f, dimension.y * 0.50f);
        textBounds = new Rectangle(position.x + dimension.x * 0.1f, position.y + dimension.y * 0.05f, dimension.x * 0.8f, dimension.y * 0.35f);

    }

    public void use(GameStats stats) {
        stats.addMoney(money);
        stats.addPollution(pollution);
    }

    @Override
    public void render(SpriteBatch batch) {
        //color for the card background, the card assets is white and then tinted
        batch.setColor(c);
        batch.draw(Assets.getInstance().card, position.x, position.y, dimension.x, dimension.y);
        batch.setColor(Color.WHITE);

        //image area
        batch.draw(Assets.getInstance().black, imageBounds.x, imageBounds.y, imageBounds.width, imageBounds.height);
        batch.draw(Assets.getInstance().getTexture(tex), imageBounds.x, imageBounds.y, imageBounds.width, imageBounds.height);

        //text area
        batch.draw(Assets.getInstance().black, textBounds.x, textBounds.y, textBounds.width, textBounds.height);
        Assets.getInstance().GameFont.draw(batch, text, textBounds.x, textBounds.y + textBounds.height * 0.95f, textBounds.width, Align.center, true);

    }

    @Override
    public void update(float elapsedTime) {

    }


}
