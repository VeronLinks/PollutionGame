package com.mygdx.game.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLogic.Player;
import com.mygdx.game.hud.HUDElement;
import com.mygdx.game.hud.Label;

public class GameStats extends HUDElement {

    private Player currentPlayer;
    private Label pollutionLabel;
    private Label moneyLabel;
    private Label volunteers;

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameStats(float x, float y) {

        pollutionLabel = new Label("", x, y);
        moneyLabel = new Label("", x + 300, y);
        volunteers = new Label("", x + 300, y);
    }



    @Override
    public void render(SpriteBatch batch) {
        if (currentPlayer != null) {
            pollutionLabel.text = "Pollution level: " + currentPlayer.pollution;
            moneyLabel.text = "Money: " + currentPlayer.money + "$";
            moneyLabel.text = "Volunteers: " + currentPlayer.volunteers;
        }
    }
}
