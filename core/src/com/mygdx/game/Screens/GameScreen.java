package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Controllers.WorldController;
import com.mygdx.game.Controllers.WorldRenderer;
import com.mygdx.game.GameLogic.GameManager;
import com.mygdx.game.GameLogic.Player;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.hud.HUD;
import com.mygdx.game.hud.TextButton;

public class GameScreen implements Screen {

    WorldController controller;
    WorldRenderer renderer;
    MyGdxGame game;

    public GameScreen(Game game) {
        this.game = (MyGdxGame)game;
    }


    @Override
    public void show() {
        controller = (game).controller;
        renderer = (game).renderer;
        /*GameManager.getInstance().playerList.clear();
        for (int i = 0; i < GameManager.players; i++)
        {
            GameManager.getInstance().playerList.add(new Player());
        }*/
        GameManager.getInstance().currentPlayer =
                GameManager.getInstance().playerList.get(GameManager.getInstance().turn);
    }

    @Override
    public void render(float delta)
    {
        controller.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resume() {


    }
    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }
}