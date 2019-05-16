package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen {

    WorldController controller;
    WorldRenderer renderer;
    Game game;
    MenuScreen menu;

    public GameScreen(Game game, MenuScreen menu) {
        this.game = game;
        this.menu = menu;
    }


    @Override
    public void show() {
        controller = new WorldController(game);
        renderer = new WorldRenderer(controller);
        controller.camera = renderer.camera;

    }

    @Override
    public void render(float delta) {

        controller.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renderer.render();

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