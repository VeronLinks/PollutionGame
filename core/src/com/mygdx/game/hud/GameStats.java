package com.mygdx.game.hud;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controllers.WorldController;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.GameLogic.Player;
import com.mygdx.game.hud.HUDElement;
import com.mygdx.game.hud.Label;

public class GameStats extends HUDElement {

    private Label pollutionLabel;
    private Label moneyLabel;
    private Label volunteers;
    private Label turnInfo;

    public GameStats(float x, float y) {

        pollutionLabel = new Label("", x, y);
        moneyLabel = new Label("", x + 300, y);
        volunteers = new Label("", x + 600, y);
        turnInfo = new Label("", x, y + WorldController.hudCamera.viewportHeight - 70);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (GameManager.getInstance().currentPlayer != null) {
            pollutionLabel.text = "Pollution level: " + GameManager.getInstance().currentPlayer.pollution;
            moneyLabel.text = "Money: " + GameManager.getInstance().currentPlayer.money + "$";
            volunteers.text = "Volunteers: " + GameManager.getInstance().currentPlayer.volunteers;
            turnInfo.text = "TURN: " + GameManager.numberOfTurns + ", Current Player: " + (GameManager.turn + 1) + " of a total of " + GameManager.players;
        }
        else
        {
            pollutionLabel.text = "Pollution level: " + "WHY";
            moneyLabel.text = "Money: " + "DOESNT";
            volunteers.text = "Volunteers: " + "THIS";
            turnInfo.text = "TURN: " + "WORK";
        }
        pollutionLabel.render(batch);
        moneyLabel.render(batch);
        volunteers.render(batch);
        turnInfo.render(batch);
    }
}
