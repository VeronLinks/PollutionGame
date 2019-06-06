package com.mygdx.game.hud;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.GameLogic.Player;
import com.mygdx.game.hud.HUDElement;
import com.mygdx.game.hud.Label;

public class GameStats extends HUDElement {

    private Label pollutionLabel;
    private Label moneyLabel;
    private Label volunteers;

    public GameStats(float x, float y) {

        pollutionLabel = new Label("", x, y);
        moneyLabel = new Label("", x + 300, y);
        volunteers = new Label("", x + 600, y);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (GameManager.getInstance().currentPlayer != null) {
            pollutionLabel.text = "Pollution level: " + GameManager.getInstance().currentPlayer.pollution;
            moneyLabel.text = "Money: " + GameManager.getInstance().currentPlayer.money + "$";
            moneyLabel.text = "Volunteers: " + GameManager.getInstance().currentPlayer.volunteers;
        }
        else
        {
            pollutionLabel.text = "Pollution level: " + "FUCK";
            moneyLabel.text = "Money: " + "MY";
            moneyLabel.text = "Volunteers: " + "LIFE";
        }
    }
}
