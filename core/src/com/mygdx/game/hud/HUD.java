package com.mygdx.game.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.Controllers.WorldController;
import com.mygdx.game.Controllers.WorldRenderer;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.GameLogic.Player;

import java.util.ArrayList;


public class HUD {

    ArrayList<TextButton> buttons;
    ArrayList<HUDElement> labels;
    String[] playersData = new String[4];

    public HUD() {
        buttons = new ArrayList<TextButton>();
        labels = new ArrayList<HUDElement>();

    }

    public void add(TextButton go) {
        buttons.add(go);
    }

    public void add(HUDElement label) {
        labels.add(label);
    }

    public void clear()
    {
        labels.clear();
        buttons.clear();
    }

    public void render(SpriteBatch batch) {
        for (HUDElement he : buttons) {
            he.render(batch);
        }
        if (GameManager.players > 1) {
            for (HUDElement he : labels) {
                he.render(batch);
            }
            renderPlayerData(batch);
        }
    }

    public boolean click(float x, float y) {

        for (TextButton b : buttons) {
            if (b.contains(x, y)) {
                b.click();
                return true;
            }
        }
        return false;
    }

    private void renderPlayerData(SpriteBatch batch){

        int x = (int)(-WorldController.camera.viewportWidth/2.6f);
        int y = (int)(-WorldController.camera.viewportHeight/2.8f);
        int w = (int) WorldController.camera.viewportWidth/25;
        int offset = (int) (WorldController.camera.viewportHeight/13f);
        Assets.getInstance().mediumFont.setColor(Color.BLACK);
        for(int i=0;i<GameManager.playerList.size();i++){
            Player p = GameManager.playerList.get(i);
            playersData[i] = "Volunteers: " + p.volunteers + "\nMoney: " + p.money;
            Assets.getInstance().mediumFont.draw(batch, playersData[i], x+w/10, y+offset*i, x, Align.left,true);
            batch.setColor(WorldRenderer.colors[i]);
            batch.draw(Assets.getInstance().box, x-w, y+offset*i-w, w, w);
        }
        Assets.getInstance().mediumFont.setColor(Color.WHITE);
    }

}
