package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;

public class MenuScreen implements Screen {

    Game game;
    BitmapFont font;
    SpriteBatch batch;
    OrthographicCamera camera;
    int playersNumber;

    public MenuScreen(Game game) {
        this.game = game;
        font = new BitmapFont();
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        System.out.println("EEEE");
        Gdx.gl.glClearColor(0f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ((MyGdxGame)game).input.update(Gdx.graphics.getDeltaTime());

        //update
        if (Gdx.input.isTouched())
        {
            if (Gdx.input.getX() < Gdx.graphics.getWidth() / 3)
            {
                playersNumber = 2;
            }
            else if (Gdx.input.getX() < Gdx.graphics.getWidth() * 2 / 3)
            {
                playersNumber = 3;
            }
            else
            {
                playersNumber = 4;
            }
            game.setScreen(((MyGdxGame) game).gameScreen);
        }

        //render
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.draw(batch,"Click Left for 2, Center for 3 or Right for 4", -camera.viewportWidth/2,0,camera.viewportWidth, Align.center,true);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
