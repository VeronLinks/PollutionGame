package com.mygdx.game.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.hud.HUDElement;
import com.mygdx.game.hud.Label;

public class GameStats extends HUDElement {

    private float pollution;
    private float money;
    private Label pollutionLabel;
    private Label moneyLabel;

    public GameStats(float x, float y) {

        pollutionLabel = new Label("", x, y);
        moneyLabel = new Label("", x + 200, y);
        addPollution(pollution);
        addMoney(money);
    }

    public void addPollution(float pollution) {
        this.pollution += pollution;
        pollutionLabel.text = "Poll: " + this.pollution;
    }

    public void addMoney(float money) {
        this.money += money;
        moneyLabel.text = "Cash: " + this.money + "$";
    }

    @Override
    public void render(SpriteBatch batch) {
        pollutionLabel.render(batch);
        moneyLabel.render(batch);
    }
}
